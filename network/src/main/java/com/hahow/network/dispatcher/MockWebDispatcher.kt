package com.hahow.network.dispatcher

import com.hahow.common.extension.safeLet
import com.hahow.common.utils.AssetsReader
import okhttp3.HttpUrl
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import kotlin.properties.Delegates

/**
 * 處理MockWebServer攔截的Request及回傳的Response
 *
 * @param assetsReader Json讀取工具 See[com.hahow.common.utils.AssetsReaderImpl]
 */
class MockWebDispatcher(private val assetsReader: AssetsReader) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        var requestPath by Delegates.notNull<String>()
        var requestUrl by Delegates.notNull<HttpUrl>()

        safeLet(request.method, request.path, request.requestUrl) { _, path, url ->
            requestPath = path
            requestUrl = url
        }

        return when {
            request.method.orEmpty() == "GET"  -> {
                mockResp(assetsPath = "json/courses.json")
            }

            else -> MockResponse()
        }
    }

    /**
     * 回傳的Response
     *
     * @param respCode ex: 200, 404
     * @param assetsPath resources 檔案路徑
     */
    private fun mockResp(respCode: Int = 200, assetsPath: String): MockResponse {
        return MockResponse()
            .setResponseCode(respCode)
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .setBody(assetsReader.getJsonFromAssets(assetsPath))
    }
}

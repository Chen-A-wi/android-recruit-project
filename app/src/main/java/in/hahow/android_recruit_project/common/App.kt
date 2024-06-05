package `in`.hahow.android_recruit_project.common

import com.hahow.network.MOCK_WEB_SERVER_PORT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.mockwebserver.MockWebServer
import org.koin.android.ext.android.inject

class App : BaseApp() {
    private val mockServer by inject<MockWebServer>()
    override fun onCreate() {
        super.onCreate()

        // 啟動MockWebServer
        CoroutineScope(Dispatchers.IO).launch {
            mockServer.start(MOCK_WEB_SERVER_PORT)
        }
    }
}

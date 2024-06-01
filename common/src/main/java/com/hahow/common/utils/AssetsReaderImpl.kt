package com.hahow.common.utils

import java.io.BufferedReader
import java.io.InputStreamReader

class AssetsReaderImpl : AssetsReader {
    /**
     * 實作 JsonReader
     * @param assetsPath 檔案路徑 ex: "json/xxx.json"
     * @return Json 檔案內容
     * @see [AssetsReader]
     */
    override fun getJsonFromAssets(assetsPath: String): String {
        val builder = StringBuilder()
        val reader = BufferedReader(
            InputStreamReader(
                javaClass.classLoader?.getResourceAsStream(assetsPath)
                    ?: throw IllegalArgumentException("Read File Failed: $assetsPath"),
            ),
        )

        var theCharNum = reader.read()
        while (theCharNum != -1) {
            builder.append(theCharNum.toChar())
            theCharNum = reader.read()
        }

        return builder.toString()
    }
}

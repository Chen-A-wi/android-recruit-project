package com.hahow.common.utils

import java.io.BufferedReader
import java.io.InputStreamReader

class AssetsReaderImpl : AssetsReader {
    override fun getFileFromAssets(assetsPath: String): String {
        val builder = StringBuilder()
        val reader = BufferedReader(
            InputStreamReader(
                javaClass.classLoader?.getResourceAsStream(assetsPath)
                    ?: throw IllegalArgumentException("Cannot find File: $assetsPath"),
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

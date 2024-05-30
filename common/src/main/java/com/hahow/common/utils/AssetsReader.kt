package com.hahow.common.utils

interface AssetsReader {
    fun getFileFromAssets(assetsPath: String): String
}

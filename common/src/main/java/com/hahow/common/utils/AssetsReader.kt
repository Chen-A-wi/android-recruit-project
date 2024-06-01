package com.hahow.common.utils

interface AssetsReader {
    fun getJsonFromAssets(assetsPath: String): String
}

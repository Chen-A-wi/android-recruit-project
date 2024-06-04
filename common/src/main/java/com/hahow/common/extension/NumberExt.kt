package com.hahow.common.extension

fun Float?.orZero(): Float = this ?: 0.0f

fun Double?.orZero(): Double = this ?: 0.0

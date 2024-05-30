package com.hahow.common

internal object DateTimeFormatPattern {
    private const val PATTERN_MONTH = "MMM"

    private const val TIME_FORMAT_H_M_S = "HH:mm:ss"
    const val TIME_FORMAT_H_M = "HH:mm"
    const val TIME_FORMAT_H_M_FILE_NAME = "HHmm"

    const val DATE_FORMAT = "yyyy-MM-dd"
    const val DATE_FORMAT_EN = "$PATTERN_MONTH/dd/yyyy"

    const val DATE_TIME_FORMAT = "$DATE_FORMAT $TIME_FORMAT_H_M_S"
    const val DATE_TIME_FORMAT_EN = "$DATE_FORMAT_EN $TIME_FORMAT_H_M_S"
}

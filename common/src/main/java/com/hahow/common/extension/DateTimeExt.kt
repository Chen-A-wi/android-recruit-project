package com.hahow.common.extension

import com.hahow.common.DateTimeFormatPattern.DATE_FORMAT
import com.hahow.common.DateTimeFormatPattern.DATE_FORMAT_EN
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

/**
 * LocalDate轉時間戳
 *
 * @param zoneId 時區
 */

fun LocalDateTime.toTimestamp(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return this.toInstant(zoneId.rules.getOffset(this)).toEpochMilli()
}

/**
 * 時間戳轉LocalDate
 *
 * @param zoneId 時區
 */
fun Long.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate {
    return Instant.ofEpochMilli(this).atZone(zoneId).toLocalDate()
}

/**
 * 文字轉LocalDate
 */
val String.localDateTimeFromISO: LocalDateTime
    get() = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)

/**
 * 相差的天數
 *
 * @param dueDateTime 截止時間
 */
fun LocalDateTime.betweenDay(dueDateTime: LocalDateTime): Long {
    return this.until(dueDateTime, ChronoUnit.DAYS)
}

/**
 * 日期格式轉文字
 *
 * @sample 英文: Dec/13/2022
 * @sample 其他: 2022-12-13
 */

fun LocalDate.text(locale: Locale = Locale.CHINESE): String {
    val pattern = when (locale.language) {
        "en" -> DATE_FORMAT_EN
        else -> DATE_FORMAT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

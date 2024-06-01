package com.hahow.common.extension

import android.icu.util.LocaleData
import com.hahow.common.DateTimeFormatPattern.DATE_FORMAT
import com.hahow.common.DateTimeFormatPattern.DATE_FORMAT_EN
import com.hahow.common.DateTimeFormatPattern.DATE_TIME_FORMAT
import com.hahow.common.DateTimeFormatPattern.DATE_TIME_FORMAT_EN
import com.hahow.common.DateTimeFormatPattern.TIME_FORMAT_H_M
import com.hahow.common.DateTimeFormatPattern.TIME_FORMAT_H_M_FILE_NAME
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale


fun LocalDate.toTimestamp(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return this.atStartOfDay(zoneId).toInstant().toEpochMilli()
}


fun LocalDateTime.toTimestamp(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return this.toInstant(zoneId.rules.getOffset(this)).toEpochMilli()
}


fun Long.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate {
    return Instant.ofEpochMilli(this).atZone(zoneId).toLocalDate()
}


fun Long.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return Instant.ofEpochMilli(this).atZone(zoneId).toLocalDateTime()
}


fun Long.toLocalTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalTime {
    return toLocalDateTime(zoneId).toLocalTime()
}

val String.localDateTimeFromISO: LocalDateTime
    get() = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)

val String.localDateFromISO: LocalDate
    get() = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)

fun LocalDateTime.betweenDay(dueDateTime: LocalDateTime): Long {
    return this.until(dueDateTime, ChronoUnit.DAYS)
}

/**
 * @return
 * 英文: Dec/13/2022
 * 其他: 2022-12-13
 */

fun LocalDate.text(locale: Locale = Locale.CHINESE): String {
    val pattern = when (locale.language) {
        "en" -> DATE_FORMAT_EN
        else -> DATE_FORMAT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

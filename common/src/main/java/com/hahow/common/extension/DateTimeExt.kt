package com.hahow.common.extension

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
import java.util.Locale


internal fun LocalDate.toTimestamp(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return this.atStartOfDay(zoneId).toInstant().toEpochMilli()
}


internal fun LocalDateTime.toTimestamp(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return this.toInstant(zoneId.rules.getOffset(this)).toEpochMilli()
}


internal fun Long.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate {
    return Instant.ofEpochMilli(this).atZone(zoneId).toLocalDate()
}


internal fun Long.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return Instant.ofEpochMilli(this).atZone(zoneId).toLocalDateTime()
}


internal fun Long.toLocalTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalTime {
    return toLocalDateTime(zoneId).toLocalTime()
}

internal val String.localDateTimeFromISO: LocalDateTime
    get() = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)

internal val String.localDateFromISO: LocalDate
    get() = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)


internal fun LocalDateTime.isBetween(
    startDateTime: LocalDateTime,
    endDateTime: LocalDateTime
): Boolean =
    this.isAfter(startDateTime) && this.isBefore(endDateTime)


internal fun LocalTime.textWithoutSec(): String = format(
    DateTimeFormatter.ofPattern(TIME_FORMAT_H_M)
)


internal fun LocalTime.formatToFileName(): String = format(
    DateTimeFormatter.ofPattern(TIME_FORMAT_H_M_FILE_NAME)
)

/**
 * @return
 * 英文: Dec/13/2022 08:08:08
 * 其他: 2022-12-13 08:08:08
 */

internal fun LocalDateTime.text(locale: Locale = Locale.CHINESE): String {
    val pattern = when (locale.language) {
        "en" -> DATE_TIME_FORMAT_EN
        else -> DATE_TIME_FORMAT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

/**
 * @return
 * 英文: Dec/13/2022
 * 其他: 2022-12-13
 */

internal fun LocalDate.text(locale: Locale = Locale.CHINESE): String {
    val pattern = when (locale.language) {
        "en" -> DATE_FORMAT_EN
        else -> DATE_FORMAT
    }
    return format(DateTimeFormatter.ofPattern(pattern, locale))
}

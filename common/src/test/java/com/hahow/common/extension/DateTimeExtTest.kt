package com.hahow.common.extension

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.temporal.ChronoUnit

class DateTimeExtTest {
    private val jsonDateTime = "2023-07-14T15:59:02Z"

    @Test
    @DisplayName("測試 String to LocalDate")
    fun stringToLocalDateTest() {
        val localDate = jsonDateTime.localDateTimeFromISO.toLocalDate()

        localDate.year shouldBe 2023
        localDate.month.value shouldBe 7
        localDate.dayOfMonth shouldBe 14
    }

    @Test
    @DisplayName("測試 String to LocalTime")
    fun stringToLocalTimeTest() {
        val localTime = jsonDateTime.localDateTimeFromISO.toLocalTime()

        localTime.hour shouldBe 15
        localTime.minute shouldBe 59
        localTime.second shouldBe 2
    }

    @Test
    @DisplayName("測試 String to LocalDateTime")
    fun stringToLocalDateTimeTest() {
        val localDateTime = jsonDateTime.localDateTimeFromISO

        localDateTime.year shouldBe 2023
        localDateTime.month.value shouldBe 7
        localDateTime.dayOfMonth shouldBe 14
        localDateTime.hour shouldBe 15
        localDateTime.minute shouldBe 59
        localDateTime.second shouldBe 2
    }

    @Test
    @DisplayName("測試 LocalDateTime Format")
    fun dateFormatTest() {
        val localDate = jsonDateTime.localDateTimeFromISO.toTimestamp().toLocalDate().text()

        localDate shouldBe "2023-07-14"
    }

    @Test
    @DisplayName("測試相差天數")
    fun betweenDayTest() {
        val fakeTodayDateTime = "2024-05-31T15:59:02Z".localDateTimeFromISO
        val dueDateTime = "2024-06-01T15:59:02Z".localDateTimeFromISO

        fakeTodayDateTime.betweenDay(dueDateTime) shouldBe 1
    }
}
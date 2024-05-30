package com.hahow.common.extension

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DateTimeExtTest {
    private val jsonDateTime = "2023-07-14T15:59:02Z"

    @Test
    @DisplayName("測試 String to LocalDate")
    fun stringToLocalDateTest() {
        val dateTime = jsonDateTime.localDateTimeFromISO.toLocalDate()

        dateTime.year shouldBe 2023
        dateTime.month.value shouldBe 7
        dateTime.dayOfMonth shouldBe 14
    }
}
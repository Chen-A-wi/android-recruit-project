package com.hahow.common.extension

import com.google.common.truth.Truth

infix fun <T> T.shouldBe(that: T) = Truth.assertThat(that).isEqualTo(this)

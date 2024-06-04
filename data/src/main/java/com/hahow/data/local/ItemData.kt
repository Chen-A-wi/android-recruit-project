package com.hahow.data.local

import androidx.annotation.StringRes
import com.hahow.common.extension.betweenDay
import com.hahow.common.extension.localDateTimeFromISO
import com.hahow.common.extension.orZero
import com.hahow.data.R
import com.hahow.domain.model.Course
import java.time.LocalDateTime

enum class CourseStateType {
    COMPULSORY, ELECTIVE, NONE
}

enum class DueDateStateType {
    PASS, OVER_DATE,
}

enum class SubscriptType {
    BY, REVIEW_BEFORE
}

data class ItemData(
    val title: String,
    val imgUrl: String,
    val dueDate: String,
    val progressValue: Float,
    val progressNum: Int,
    val subscripts: SubscriptsData,
    val courseState: CourseStateType,
    val isTenant: Boolean,
)

data class SubscriptsData(
    val isVisible: Boolean,
    val type: SubscriptType,
    val cpValue: String,
)

fun List<Course>.beItemViewData(): List<ItemData> = map { course ->
    ItemData(
        title = course.title.orEmpty(),
        dueDate = "",
        subscripts = getSubscriptsType(course),
        courseState = CourseStateType.COMPULSORY,
        isTenant = course.source?.equals("TENANT_COURSE") ?: false,
        imgUrl = course.coverImageUrl.orEmpty(),
        progressValue = course.completionPercentage.orZero().toFloat(),
        progressNum = (course.completionPercentage.orZero() * 100).toInt()
    )
}

private fun getSubscriptsType(course: Course): SubscriptsData {
    val name = course.recentStartedAssignment?.assigners?.first()?.name.orEmpty()
    val today = LocalDateTime.now()

    return SubscriptsData(
        isVisible = name.isNotEmpty() || (course.lastViewedAt?.isNotEmpty() == true),
        type = if (name.isNotEmpty()) {
            SubscriptType.BY
        } else {
            SubscriptType.REVIEW_BEFORE
        },
        cpValue = name.ifEmpty {
            course.lastViewedAt?.localDateTimeFromISO?.betweenDay(today)?.toString().orEmpty()
        }
    )
}

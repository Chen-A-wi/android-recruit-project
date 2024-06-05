package com.hahow.data.local

import com.hahow.common.extension.betweenDay
import com.hahow.common.extension.localDateTimeFromISO
import com.hahow.common.extension.orZero
import com.hahow.common.extension.text
import com.hahow.common.extension.toLocalDate
import com.hahow.common.extension.toTimestamp
import com.hahow.data.local.DueDateStateType.BETWEEN_DATE
import com.hahow.data.local.DueDateStateType.DEADLINE
import com.hahow.data.local.DueDateStateType.FOREVER
import com.hahow.data.local.DueDateStateType.OVER_DATE
import com.hahow.data.local.DueDateStateType.PASS_DATE
import com.hahow.data.local.DueDateStateType.TODAY
import com.hahow.data.local.SubscriptType.BY
import com.hahow.data.local.SubscriptType.REVIEW_BEFORE
import com.hahow.domain.model.Course
import java.time.LocalDateTime
import kotlin.properties.Delegates

enum class CourseStateType {
    COMPULSORY, ELECTIVE, NONE
}

/**
 * 截止日期狀態
 * @sample PASS_DATE 已觀看日期
 * @sample OVER_DATE 已過期
 * @sample TODAY 今日內 ex: 1 天內截止
 * @sample BETWEEN_DATE 1~7天內 ex: 截止日剩X天
 * @sample FOREVER 無期限
 * @sample DEADLINE 8 日以上 ex: yyyy-MM-dd 截止
 */
enum class DueDateStateType {
    PASS_DATE, OVER_DATE, TODAY, BETWEEN_DATE, FOREVER, DEADLINE
}

/**
 * 右下角角標狀態
 * @sample BY XX指派
 * @sample REVIEW_BEFORE XX天前觀看
 */
enum class SubscriptType {
    BY, REVIEW_BEFORE
}

data class ItemData(
    val title: String,
    val imgUrl: String,
    val dueData: DueData,
    val progressValue: Float,
    val progressNum: Int,
    val subscripts: SubscriptsData,
    val courseState: CourseStateType,
    val isTenant: Boolean,
    val course: Course,
)

data class SubscriptsData(
    val isVisible: Boolean,
    val type: SubscriptType,
    val cpValue: String,
)

data class DueData(
    val status: DueDateStateType,
    val cpValue: String,
)

/**
 * 轉換成列表顯示需要的資料
 * @return List<ItemData>
 * See[ItemData]
 */
fun List<Course>.beItemViewData(): List<ItemData> = map { course ->
    ItemData(
        title = course.title.orEmpty(),
        dueData = getDueData(course),
        subscripts = getSubscriptsType(course),
        courseState = getCourseType(course),
        isTenant = course.source?.equals("TENANT_COURSE") ?: false,
        imgUrl = course.coverImageUrl.orEmpty(),
        progressValue = course.completionPercentage.orZero().toFloat(),
        progressNum = (course.completionPercentage.orZero() * 100).toInt(),
        course = course
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

private fun getCourseType(course: Course): CourseStateType {
    return when (course.recentStartedAssignment?.rule.orEmpty()) {
        CourseStateType.COMPULSORY.name -> {
            CourseStateType.COMPULSORY
        }

        CourseStateType.ELECTIVE.name -> {
            CourseStateType.ELECTIVE
        }

        else -> {
            CourseStateType.NONE
        }
    }
}

private fun getDueData(course: Course): DueData {
    var status by Delegates.notNull<DueDateStateType>()
    var cpValue by Delegates.notNull<String>()

    if (
        course.studiedAt.isNullOrEmpty() && course.recentStartedAssignment?.timeline?.dueAt.isNullOrEmpty()
    ) {
        status = FOREVER
        cpValue = ""
    } else if (course.studiedAt?.isNotEmpty() == true) {
        status = PASS_DATE
        cpValue =
            course.studiedAt?.localDateTimeFromISO?.toTimestamp()?.toLocalDate()?.text().orEmpty()
    } else {
        checkBetweenDate(course.recentStartedAssignment?.timeline?.dueAt.orEmpty()).let { (type, value) ->
            status = type
            cpValue = value
        }
    }

    return DueData(
        status = status,
        cpValue = cpValue
    )
}

private fun checkBetweenDate(dueAt: String): Pair<DueDateStateType, String> {
    val betweenDay = LocalDateTime.now().betweenDay(dueAt.localDateTimeFromISO)

    return when {
        betweenDay.toInt() == 0 -> {
            TODAY to "1"
        }

        betweenDay < 0 -> {
            OVER_DATE to ""
        }

        betweenDay in 1..7 -> {
            BETWEEN_DATE to betweenDay.toInt().toString()
        }

        else -> {
            DEADLINE to dueAt.localDateTimeFromISO.toTimestamp().toLocalDate()
                .text()
        }
    }
}

package com.hahow.data.schema


import com.hahow.domain.model.Timeline
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimelineSchema(
    @SerialName("dueAt")
    val dueAt: String?,
    @SerialName("startAt")
    val startAt: String?,
    @SerialName("__typename")
    val typename: String?
)

internal fun TimelineSchema.toDomain(): Timeline = Timeline(
    dueAt = dueAt,
    startAt = startAt,
    typename = typename

)
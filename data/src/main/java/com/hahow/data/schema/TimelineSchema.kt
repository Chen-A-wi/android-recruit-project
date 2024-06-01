package com.hahow.data.schema


import com.hahow.domain.model.Timeline
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimelineSchema(
    @SerialName("dueAt")
    val dueAt: String? = null,
    @SerialName("startAt")
    val startAt: String? = null,
    @SerialName("__typename")
    val typename: String? = null
)

internal fun TimelineSchema.toDomain(): Timeline = Timeline(
    dueAt = dueAt,
    startAt = startAt,
    typename = typename

)
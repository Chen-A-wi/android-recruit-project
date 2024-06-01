package com.hahow.data.schema


import com.hahow.domain.model.RecentStartedAssignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentStartedAssignmentSchema(
    @SerialName("assigners")
    val assigners: List<AssignerSchema?>? = null,
    @SerialName("completedAt")
    val completedAt: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("rule")
    val rule: String? = null,
    @SerialName("timeline")
    val timeline: TimelineSchema? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("__typename")
    val typename: String? = null
)

internal fun RecentStartedAssignmentSchema.toDomain() = RecentStartedAssignment(
    assigners = assigners?.toDomain(),
    completedAt = completedAt,
    id = id,
    rule = rule,
    timeline = timeline?.toDomain(),
    title = title,
    typename = typename
)
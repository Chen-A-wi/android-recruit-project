package com.hahow.data.schema


import com.hahow.domain.model.RecentStartedAssignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentStartedAssignmentSchema(
    @SerialName("assigners")
    val assigners: List<AssignerSchema?>?,
    @SerialName("completedAt")
    val completedAt: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("rule")
    val rule: String?,
    @SerialName("timeline")
    val timeline: TimelineSchema?,
    @SerialName("title")
    val title: String?,
    @SerialName("__typename")
    val typename: String?
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
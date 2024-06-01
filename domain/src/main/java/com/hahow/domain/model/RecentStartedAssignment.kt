package com.hahow.domain.model

data class RecentStartedAssignment(
    val assigners: List<Assigner?>?,
    val completedAt: String?,
    val id: String?,
    val rule: String?,
    val timeline: Timeline?,
    val title: String?,
    val typename: String?
)

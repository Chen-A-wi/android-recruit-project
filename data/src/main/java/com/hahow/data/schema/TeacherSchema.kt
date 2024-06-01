package com.hahow.data.schema


import com.hahow.domain.model.Teacher
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherSchema(
    @SerialName("name")
    val name: String?,
    @SerialName("__typename")
    val typename: String?
)

internal fun TeacherSchema.toDomain() = Teacher(name = name, typename = typename)
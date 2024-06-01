package com.hahow.data.schema


import com.hahow.domain.model.Assigner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssignerSchema(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("__typename")
    val typename: String? = null
)

internal fun List<AssignerSchema?>.toDomain(): List<Assigner?> = mapNotNull { it?.toDomain() }
internal fun AssignerSchema.toDomain(): Assigner = Assigner(
    id = id,
    name = name,
    typename = typename
)
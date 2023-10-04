package data.model

import kotlinx.serialization.Serializable

@Serializable
data class TotalContent(
    val events: Long
)
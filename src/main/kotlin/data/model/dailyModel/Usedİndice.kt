package data.model.dailyModel

import kotlinx.serialization.Serializable

@Serializable
data class Usedİndice(
    val begin: String,
    val calculated_at: String,
    val end: String,
    val index_name: String,
    val took_ms: Int
)
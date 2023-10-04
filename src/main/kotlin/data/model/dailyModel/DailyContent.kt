package data.model.dailyModel

import kotlinx.serialization.Serializable

@Serializable
data class DailyContent(
    val built_query: String,
    val decoration_stats: Int?,
    val fields: List<String>,
    val from: String,
    val messages: List<Message>,
    val query: String,
    val time: Int,
    val to: String,
    val total_results: Int,
    val used_indices: List<Usedİndice>
)
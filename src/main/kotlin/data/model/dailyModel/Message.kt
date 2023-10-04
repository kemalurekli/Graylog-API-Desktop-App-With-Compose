package data.model.dailyModel

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val decoration_stats: Int?,
    val highlight_ranges: HighlightRanges,
    val index: String,
    val message: MessageX
)
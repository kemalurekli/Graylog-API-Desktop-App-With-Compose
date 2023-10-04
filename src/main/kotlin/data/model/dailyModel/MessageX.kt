package data.model.dailyModel

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageX(
    val CLIENT_IP: String,
    val CLIENT_MAC: String,
    val COMMONMAC: String,
    val DST_IP: String,
    @SerialName("D_USG-MAC")
    val D_USGMAC: String,
    val HOUR: String,
    val MINUTE: String,
    val MONTH: String,
    val MONTHDAY: String,
    val SECOND: String,
    val SYSLOGTIMESTAMP: String,
    val TIME: String,
    val _id: String,
    val gl2_remote_ip: String,
    val gl2_remote_port: Int,
    val gl2_source_input: String,
    val gl2_source_node: String,
    val message: String,
    val source: String,
    val streams: List<String>,
    val timestamp: String
)
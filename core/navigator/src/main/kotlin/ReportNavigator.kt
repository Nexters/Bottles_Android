import kotlinx.serialization.Serializable

@Serializable
data class ReportNavigator(
    val userImageUrl: String,
    val userName: String,
    val userAge: Int
)
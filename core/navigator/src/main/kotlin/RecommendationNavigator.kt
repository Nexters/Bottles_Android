import kotlinx.serialization.Serializable

@Serializable
data object RecommendationNavigator

@Serializable
data class RecommendationDetailNavigator(
    val href: String
)
import kotlinx.serialization.Serializable

sealed interface ProfileNavigator {

    @Serializable
    data object CreateProfile: ProfileNavigator

    @Serializable
    data object Introduction: ProfileNavigator

}
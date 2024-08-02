import kotlinx.serialization.Serializable

sealed interface LoginNavigator {

    @Serializable
    data object SmsLogin: LoginNavigator

    @Serializable
    data object Endpoint: LoginNavigator

}
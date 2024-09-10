import kotlinx.serialization.Serializable

sealed interface SettingNavigator {

    @Serializable
    data object Notification : SettingNavigator

    @Serializable
    data object Account : SettingNavigator

}

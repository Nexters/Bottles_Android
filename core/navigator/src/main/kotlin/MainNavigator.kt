import kotlinx.serialization.Serializable

sealed class MainNavigator {

    @Serializable
    data object SandBeach : MainNavigator()

    @Serializable
    data object Like : MainNavigator()

    @Serializable
    data object BottlesBox : MainNavigator()

    @Serializable
    data object MyPage : MainNavigator()

}

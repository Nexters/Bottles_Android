import kotlinx.serialization.Serializable

sealed class BottleNavigator {

    @Serializable
    data object ArrivedBottles : MainNavigator()

    @Serializable
    data object Bottle : MainNavigator()

}
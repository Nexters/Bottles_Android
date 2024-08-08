import kotlinx.serialization.Serializable

sealed class BottleNavigator {

    @Serializable
    data object ArrivedBottles : MainNavigator()

    @Serializable
    data class Bottle(val bottleId: Int) : MainNavigator()

}
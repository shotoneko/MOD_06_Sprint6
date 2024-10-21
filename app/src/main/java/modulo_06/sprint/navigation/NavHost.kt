package modulo_06.sprint.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import modulo_06.sprint.room.Phone
import modulo_06.sprint.views.DetailView
import modulo_06.sprint.views.HomeView
import modulo_06.sprint.views.PhoneModel

import kotlin.reflect.typeOf

@Composable
fun PhoneNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.MainScreen,
        modifier = modifier
    )
    {
        composable<NavRoute.MainScreen>{
            HomeView(
                navigateToDetailScreen = { phone ->
                    navController.navigate(NavRoute.DetailScreen(phone))
                }
            )
        }

        composable<NavRoute.DetailScreen>(
            typeMap = mapOf(typeOf<Phone>() to NavType.mapper<PhoneModel>()),

        ) {
            val phoneModel = it.toRoute<NavRoute.DetailScreen>().phone
            DetailView(
                phone = phoneModel,
                navigateBack = { navController.popBackStack()},

                )
        }



    }
}


sealed interface NavRoute{
    @Serializable
    object MainScreen : NavRoute

    @Serializable
    data class DetailScreen(val phone: PhoneModel) : NavRoute

}
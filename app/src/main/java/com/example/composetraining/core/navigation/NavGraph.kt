package com.example.composetraining.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetraining.core.navigation.route.CategoryDetailsRoute
import com.example.composetraining.core.navigation.route.CategoryListRoute
import com.example.composetraining.core.navigation.route.RandomMealRoute
import com.example.composetraining.feature.meal_categories.viewmodel.CategoriesViewModel
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel

@Composable
fun NavigationSystem() {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreen.RandomMeal.route) {
        composable(NavScreen.RandomMeal.route) {
            val viewModel: RandomMealViewModel =
                hiltViewModel(viewModelStoreOwner = viewModelStoreOwner)
            RandomMealRoute(navController, viewModel)
        }
        composable(NavScreen.CategoryList.route) {
            val viewModel: CategoriesViewModel =
                hiltViewModel(viewModelStoreOwner = viewModelStoreOwner)
            CategoryListRoute(navController, viewModel)
        }
        composable(
            route = NavScreen.CategoryDetails.route + "/{$PARAM_ID}/{$PARAM_NAME}",
            arguments = listOf(
                navArgument(PARAM_ID) { type = NavType.StringType },
                navArgument(PARAM_NAME) { type = NavType.StringType }
            )
        ) {
            it.arguments?.let { args ->
                CategoryDetailsRoute(
                    args.getString(PARAM_ID).orEmpty(),
                    args.getString(PARAM_NAME).orEmpty(),
                    navController
                )
            }
        }
    }
}

const val PARAM_ID = "id"
const val PARAM_NAME = "name"
package denys.diomaxius.simplerecipe.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.simplerecipe.ui.screens.RecipeFormScreen
import denys.diomaxius.simplerecipe.ui.screens.RecipesScreen
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel


@Composable
fun AppNavGraph(
    viewModel: RecipeScreenViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = RecipeRoute.RecipesScreen.title
    ) {
        composable(route = RecipeRoute.RecipesScreen.title) {
            RecipesScreen(
                viewModel = viewModel,
                navHostController = navHostController
            )
        }

        composable(route = RecipeRoute.RecipeFormScreen.title) {
            RecipeFormScreen(
                navHostController = navHostController,
                addRecipe = viewModel::addRecipe
            )
        }
    }
}
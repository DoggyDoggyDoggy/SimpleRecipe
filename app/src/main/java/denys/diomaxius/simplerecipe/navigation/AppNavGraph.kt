package denys.diomaxius.simplerecipe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.simplerecipe.data.Recipe
import denys.diomaxius.simplerecipe.ui.screens.RecipeFormScreen
import denys.diomaxius.simplerecipe.ui.screens.RecipeScreen
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
                viewModel = viewModel
            )
        }

        composable(route = "${RecipeRoute.RecipeScreen.title}/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")!!.toInt()
            var recipe by remember { mutableStateOf<Recipe?>(null) }

            LaunchedEffect(recipeId) {
                recipe = viewModel.getRecipe(recipeId)
            }

            recipe?.let {
                RecipeScreen(
                    navHostController = navHostController,
                    recipe = it
                )
            }
        }
    }
}
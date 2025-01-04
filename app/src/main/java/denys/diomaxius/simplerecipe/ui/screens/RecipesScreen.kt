package denys.diomaxius.simplerecipe.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import denys.diomaxius.simplerecipe.navigation.RecipeRoute
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel


@Composable
fun RecipesScreen(
    viewModel: RecipeScreenViewModel,
    navHostController: NavHostController
) {

    val recipeList by viewModel.recipesList.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(RecipeRoute.RecipeFormScreen.title)
                }
            ) {

            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(recipeList) {
                Row(
                    modifier = Modifier.clickable { /*Navigation to different screen*/ }
                ) {
                    Text(text = it.title)
                }
            }
        }
    }
}
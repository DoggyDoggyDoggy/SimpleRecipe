package denys.diomaxius.simplerecipe.ui.screens.recipeform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel

@Composable
fun RecipeFormScreen(
    navHostController: NavHostController,
    viewModel: RecipeScreenViewModel,
    recipeId: Int?
) {
    val recipe = viewModel.recipe.observeAsState()

    LaunchedEffect(recipeId) {
        if (recipeId != null) {
            viewModel.loadRecipe(recipeId)
        } else {
            viewModel.resetFields()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
            .padding(15.dp)
            .padding(top = 30.dp)
    ) {
        Fields(viewModel = viewModel, recipe = recipe)

        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Categories(viewModel = viewModel, recipe = recipe)

        Buttons(
            viewModel = viewModel,
            recipe = recipe,
            navHostController = navHostController,
            recipeId = recipeId
        )
    }
}
package denys.diomaxius.simplerecipe.ui.screens.recipeform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import denys.diomaxius.simplerecipe.R
import denys.diomaxius.simplerecipe.data.Recipe
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel

@Composable
fun Buttons(
    viewModel: RecipeScreenViewModel,
    recipe: State<Recipe?>,
    navHostController: NavHostController,
    recipeId: Int?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                viewModel.resetFields()
                navHostController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = ButtonDefaults.outlinedButtonBorder
        ) {
            Text(text = stringResource(R.string.cancel))
        }

        Button(
            onClick = {
                if (recipeId == null) viewModel.addRecipe() else viewModel.updateRecipe(recipeId)
                viewModel.resetFields()
                navHostController.popBackStack()
            },
            enabled = enableButton(recipe.value!!)
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}

fun enableButton(recipe: Recipe): Boolean {
    return recipe.title.isNotBlank() && recipe.description.isNotBlank() && recipe.recipe.isNotBlank()
}

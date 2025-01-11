package denys.diomaxius.simplerecipe.ui.screens.recipeform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import denys.diomaxius.simplerecipe.R
import denys.diomaxius.simplerecipe.data.Recipe
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel

@Composable
fun Categories(
    viewModel: RecipeScreenViewModel,
    recipe: State<Recipe?>
) {
    Column {
        Text(
            text = stringResource(R.string.categories),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
        recipe.value!!.categories.categories.forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = category.isChecked,
                    onCheckedChange = { viewModel.toggleCategoryChecked(category) }
                )

                Text(text = category.name)
            }
        }
    }
}
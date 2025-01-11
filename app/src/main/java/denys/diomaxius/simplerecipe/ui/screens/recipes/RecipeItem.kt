package denys.diomaxius.simplerecipe.ui.screens.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.simplerecipe.data.Recipe
import denys.diomaxius.simplerecipe.utils.returnMainCategory

@Composable
fun RecipeItem(
    recipe: Recipe,
    navigate: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable { navigate(recipe.id) }
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recipe.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    returnMainCategory(recipe.categories.categories).forEach {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = it,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                Divider()

                Text(
                    text = recipe.description,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    fontSize = 16.sp,
                    softWrap = true,
                    maxLines = 3
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeItemPreview() {
    RecipeItem(
        recipe = Recipe(
            title = "Pancake",
            description = "Fluffy, golden pancakes that are perfect for breakfast or brunch.",
            recipe = ""
        ),
        navigate = { }
    )
}
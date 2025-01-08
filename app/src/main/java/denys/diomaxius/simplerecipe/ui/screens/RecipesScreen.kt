package denys.diomaxius.simplerecipe.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import denys.diomaxius.simplerecipe.data.Category
import denys.diomaxius.simplerecipe.data.Recipe
import denys.diomaxius.simplerecipe.navigation.RecipeRoute
import denys.diomaxius.simplerecipe.utils.returnMainCategory
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
            ){}
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(recipeList) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    navigate = { recipeId ->
                        navHostController.navigate("${RecipeRoute.RecipeScreen.title}/$recipeId")
                    }
                )
            }
        }
    }
}

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

                    returnMainCategory(recipe.categories.categories).forEach{
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = it
                        )
                    }
                }

                Divider()

                Text(
                    text = recipe.description,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    softWrap = true
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
        navigate = {  }
    )
}



















package denys.diomaxius.simplerecipe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.simplerecipe.data.Categories
import denys.diomaxius.simplerecipe.data.Recipe

@Composable
fun RecipeScreen(
    recipe: Recipe,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = recipe.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Divider(
            modifier = Modifier.width(250.dp),
            thickness = 3.dp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Description:",
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium
        )

        Text(text = recipe.description)

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            recipe.categories.categories.forEach {
                if (it.isChecked) {
                    Text(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        text = it.name,

                    )
                }
            }


        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Recipe:",
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium
        )

        Text(text = recipe.recipe)
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview() {
    val dummyCategories = Categories()
    dummyCategories.categories.forEach {
        it.isChecked = true
    }
    RecipeScreen(
        Recipe(
            title = "Classic Pancakes",
            description = "Fluffy, golden pancakes that are perfect for breakfast or brunch.",
            recipe = "Ingredients:\n" +
                    "1 cup all-purpose flour\n" +
                    "2 tbsp sugar\n" +
                    "1 tsp baking powder\n" +
                    "1/2 tsp baking soda\n" +
                    "1/4 tsp salt\n" +
                    "3/4 cup milk\n" +
                    "1 egg\n" +
                    "2 tbsp melted butter or oil\n" +
                    "Instructions:\n" +
                    "In a bowl, mix flour, sugar, baking powder, baking soda, and salt.\n" +
                    "In another bowl, whisk together milk, egg, and melted butter.\n" +
                    "Combine wet and dry ingredients; mix until just combined (batter will be slightly lumpy).\n" +
                    "Heat a non-stick skillet over medium heat and lightly grease it.\n" +
                    "Pour 1/4 cup batter onto the skillet for each pancake.\n" +
                    "Cook until bubbles form on the surface, then flip and cook until golden brown.\n" +
                    "Serve with butter, syrup, or your favorite toppings.",
            categories = dummyCategories

        ),
        navHostController = rememberNavController()
    )
}
package denys.diomaxius.simplerecipe.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.simplerecipe.data.Categories
import denys.diomaxius.simplerecipe.data.Recipe
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel

@Composable
fun RecipeScreen(
    recipe: Recipe,
    navHostController: NavHostController,
    recipeId: Int,
    viewModel: RecipeScreenViewModel
) {
    Scaffold(
        topBar = {
            TopBar(
                prevPage = { navHostController.popBackStack() },
                deleteRecipe = { viewModel.deleteRecipe(recipeId = recipeId) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Name(
                title = recipe.title
            )

            Divider(
                modifier = Modifier.width(250.dp),
                thickness = 3.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Description(
                description = recipe.description
            )

            Spacer(modifier = Modifier.height(20.dp))

            CategoriesUI(
                categories = recipe.categories
            )

            Spacer(modifier = Modifier.height(20.dp))

            RecipeUI(
                recipe = recipe.recipe
            )
        }
    }

}

@Composable
fun TopBar(
    prevPage: () -> Unit,
    deleteRecipe: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = prevPage) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Go back"
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = prevPage) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit"
            )
        }

        IconButton(
            onClick = { showDeleteDialog = true }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }

        DeleteRecipeDialog(
            showDeleteDialog = showDeleteDialog,
            dismiss = {showDeleteDialog = false},
            deleteRecipe = deleteRecipe,
            prevPage = prevPage
        )
    }
}


@Composable
fun DeleteRecipeDialog(
    dismiss: () -> Unit,
    showDeleteDialog: Boolean,
    deleteRecipe: () -> Unit,
    prevPage: () -> Unit
) {
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = dismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        deleteRecipe()
                        prevPage()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = dismiss
                ) {
                    Text("Dismiss")
                }
            },
            title = {
                Text(text = "Delete Recipe")
            },
            text = {
                Text(text = "Are you sure you want to delete recipe?")
            }
        )
    }
}

@Composable
fun Name(title: String) {
    Text(
        text = title,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Description(description: String) {
    Text(
        text = "Description:",
        fontSize = 26.sp,
        fontWeight = FontWeight.Medium
    )

    Text(text = description)
}

@Composable
fun CategoriesUI(categories: Categories) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Category:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        categories.categories.forEach {
            if (it.isChecked) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = it.name
                )
            }
        }
    }
}

@Composable
fun RecipeUI(recipe: String) {
    Text(
        text = "Recipe:",
        fontSize = 26.sp,
        fontWeight = FontWeight.Medium
    )

    Text(text = recipe)
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
        navHostController = rememberNavController(),
        recipeId = 1,
        viewModel = viewModel()
    )
}
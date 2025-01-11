package denys.diomaxius.simplerecipe.ui.screens.recipe

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.flowlayout.FlowRow
import denys.diomaxius.simplerecipe.R
import denys.diomaxius.simplerecipe.data.Categories
import denys.diomaxius.simplerecipe.data.Recipe
import denys.diomaxius.simplerecipe.navigation.RecipeRoute
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
                deleteRecipe = { viewModel.deleteRecipe(recipeId = recipeId) },
                editRecipe = { navHostController.navigate("${RecipeRoute.RecipeFormScreen.title}?recipeId=$recipeId") }

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
        text = stringResource(R.string.description),
        fontSize = 26.sp,
        fontWeight = FontWeight.Medium
    )

    Text(text = description)
}

@Composable
fun CategoriesUI(categories: Categories) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = stringResource(R.string.category),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 5.dp,
            crossAxisSpacing = 5.dp
        ) {
        categories.categories.forEach {
            if (it.isChecked) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = it.name
                )
            }
        }}

    }
}

@Composable
fun RecipeUI(recipe: String) {
    Text(
        text = stringResource(R.string.recipe),
        fontSize = 26.sp,
        fontWeight = FontWeight.Medium
    )

    Text(text = recipe)
}
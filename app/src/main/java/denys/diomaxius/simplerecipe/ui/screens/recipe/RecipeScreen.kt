package denys.diomaxius.simplerecipe.ui.screens.recipe

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
                color = MaterialTheme.colorScheme.secondary,
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
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun Description(description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 5.dp),
        ) {
            Text(
                text = stringResource(R.string.description),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary

            )

            Text(text = description)
        }
    }
}

@Composable
fun CategoriesUI(categories: Categories) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.category),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.width(10.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                mainAxisSpacing = 5.dp,
                crossAxisSpacing = 5.dp
            ) {
                categories.categories.forEach {
                    if (it.isChecked) {
                        Text(
                            text = it.name
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeUI(recipe: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 5.dp),
        ) {
            Text(
                text = stringResource(R.string.recipe),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(text = recipe)
        }
    }
}
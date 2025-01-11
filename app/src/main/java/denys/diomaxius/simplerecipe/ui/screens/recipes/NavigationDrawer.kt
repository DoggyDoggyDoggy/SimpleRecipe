package denys.diomaxius.simplerecipe.ui.screens.recipes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import denys.diomaxius.simplerecipe.R
import denys.diomaxius.simplerecipe.data.CategoryName
import denys.diomaxius.simplerecipe.data.allRecipes
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel

@Composable
fun NavigationDrawer(viewModel : RecipeScreenViewModel) {
    ModalDrawerSheet(
        modifier = Modifier.width(200.dp),
        drawerContainerColor = MaterialTheme.colorScheme.secondary,
        drawerContentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        Text(stringResource(R.string.menu), modifier = Modifier.padding(16.dp))

        Divider()

        NavigationDrawerItem(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .padding(top = 10.dp),
            label = {
                Text(text = allRecipes)
            },
            selected = false,
            onClick = { viewModel.sortRecipeListByCategory(allRecipes) }
        )

        NavigationDrawerItem(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .padding(top = 10.dp),
            label = {
                Text(text = CategoryName.EasyToCook.categoryName)
            },
            selected = false,
            onClick = {
                viewModel.sortRecipeListByCategory(CategoryName.EasyToCook.categoryName)
            }
        )

        NavigationDrawerItem(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .padding(top = 10.dp),
            label = {
                Text(text = CategoryName.Vegetarian.categoryName)
            },
            selected = false,
            onClick = {
                viewModel.sortRecipeListByCategory(CategoryName.Vegetarian.categoryName)
            }
        )
    }
}
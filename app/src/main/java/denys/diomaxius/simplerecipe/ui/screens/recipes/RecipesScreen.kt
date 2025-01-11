package denys.diomaxius.simplerecipe.ui.screens.recipes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import denys.diomaxius.simplerecipe.R
import denys.diomaxius.simplerecipe.data.allRecipes
import denys.diomaxius.simplerecipe.navigation.RecipeRoute
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel


@Composable
fun RecipesScreen(
    viewModel: RecipeScreenViewModel,
    navHostController: NavHostController
) {

    val recipeList by viewModel.recipesList.observeAsState(emptyList())
    val categoryName by viewModel.categoryName.observeAsState(allRecipes)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawer(viewModel = viewModel)
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    drawerState = drawerState,
                    scope = scope,
                    categoryName = categoryName
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.clip(shape = CircleShape),
                    onClick = {
                        navHostController.navigate(RecipeRoute.RecipeFormScreen.title)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add)
                    )
                }
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
}






















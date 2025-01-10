package denys.diomaxius.simplerecipe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import denys.diomaxius.simplerecipe.data.CategoryName
import denys.diomaxius.simplerecipe.data.Recipe
import denys.diomaxius.simplerecipe.data.allRecipes
import denys.diomaxius.simplerecipe.navigation.RecipeRoute
import denys.diomaxius.simplerecipe.utils.returnMainCategory
import denys.diomaxius.simplerecipe.viewmodel.RecipeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


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
            ModalDrawerSheet(
                modifier = Modifier.width(250.dp),
                drawerContainerColor = Color.Gray,
                drawerContentColor = Color.White
            ) {
                Text("Menu", modifier = Modifier.padding(16.dp))

                Divider()

                NavigationDrawerItem(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .padding(top = 10.dp),
                    label = {
                        Text(text = allRecipes)
                    },
                    selected = false,
                    onClick = { viewModel.sortRecipeListByCategory(allRecipes)}
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
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
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

@Composable
fun TopBar(drawerState: DrawerState, scope: CoroutineScope, categoryName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply { if (isClosed) open() else close() }
                }
            }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }
        Text(
            text = categoryName,
            modifier = Modifier.align(Alignment.Center)
        )
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
        navigate = { }
    )
}
















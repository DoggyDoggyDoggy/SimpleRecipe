package denys.diomaxius.simplerecipe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun RecipeFormScreen(
    navHostController: NavHostController,
    viewModel: RecipeScreenViewModel
) {
    val name = viewModel.recipeTitle.observeAsState()
    val description = viewModel.description.observeAsState()
    val recipe = viewModel.recipeCook.observeAsState()
    val categories = viewModel.categories.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .padding(top = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.width(230.dp),
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Enter name of your dish",
                    )
                },
                value = name.value ?: "",
                maxLines = 2,
                onValueChange = { viewModel.updateRecipeTitle(it) }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Enter short description",
                    )
                },
                value = description.value ?: "",
                minLines = 5,
                onValueChange = { viewModel.updateDescription(it) }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Enter your recipe",
                    )
                },
                value = recipe.value ?: "",
                minLines = 10,
                onValueChange = { viewModel.updateRecipe(it) }
            )
        }

        Divider(modifier = Modifier.padding(vertical = 15.dp))

        Column {
            Text(
                text = "Categories:",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            categories.value!!.categories.forEach { category ->
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


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navHostController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Text(text = "Cancel")
            }

            Button(
                onClick = {
                    viewModel.addRecipe()
                    navHostController.popBackStack()
                },
                enabled = !name.value.isNullOrBlank() && !description.value.isNullOrBlank() && !recipe.value.isNullOrBlank()
            ) {
                Text(text = "Save")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeFormScreenPreview() {
    RecipeFormScreen(
        viewModel = viewModel(),
        navHostController = rememberNavController()
    )
}
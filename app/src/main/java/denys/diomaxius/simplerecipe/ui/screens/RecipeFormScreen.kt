package denys.diomaxius.simplerecipe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.simplerecipe.data.Recipe

@Composable
fun RecipeFormScreen(
    navHostController: NavHostController,
    addRecipe: (Recipe) -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var recipe by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .padding(top = 30.dp),

    ) {
        Column(
            modifier = Modifier.weight(1f),
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
                value = name,
                maxLines = 2,
                onValueChange = { name = it }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Enter short description",
                    )
                },
                value = description,
                minLines = 5,
                onValueChange = { description = it }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Enter your recipe",
                    )
                },
                value = recipe,
                minLines = 10,
                onValueChange = { recipe = it }
            )
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
                    addRecipe(
                        Recipe(
                            title = name,
                            description = description,
                            recipe = recipe
                        )
                    )
                    navHostController.popBackStack()
                },
                enabled = name.isNotEmpty() && description.isNotEmpty() && recipe.isNotEmpty()
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
        addRecipe = {},
        navHostController = rememberNavController()
    )
}
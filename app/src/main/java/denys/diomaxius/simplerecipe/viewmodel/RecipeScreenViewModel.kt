package denys.diomaxius.simplerecipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import denys.diomaxius.simplerecipe.MyApp
import denys.diomaxius.simplerecipe.data.Recipe
import kotlinx.coroutines.launch

class RecipeScreenViewModel : ViewModel() {
    val recipeDao = MyApp.recipeDatabase.getRecipeDao()

    val recipesList: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    fun addRecipe() {
        viewModelScope.launch {
            recipeDao.addRecipe(
                Recipe(
                    title = "Test",
                    description = "",
                    recipe = ""
                )
            )
        }
    }
}
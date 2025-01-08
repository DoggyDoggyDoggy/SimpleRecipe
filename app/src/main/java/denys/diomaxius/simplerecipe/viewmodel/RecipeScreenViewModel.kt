package denys.diomaxius.simplerecipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import denys.diomaxius.simplerecipe.MyApp
import denys.diomaxius.simplerecipe.data.Categories
import denys.diomaxius.simplerecipe.data.Category
import denys.diomaxius.simplerecipe.data.Recipe
import kotlinx.coroutines.launch

class RecipeScreenViewModel : ViewModel() {
    private val recipeDao = MyApp.recipeDatabase.getRecipeDao()
    val recipesList: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    private val _recipe = MutableLiveData<Recipe>(
        Recipe(
            title = "",
            description = "",
            recipe = "",
            categories = Categories()
        )
    )
    val recipe: LiveData<Recipe> = _recipe

    suspend fun loadRecipe(recipeId: Int) {
        val recipe: Recipe = getRecipe(recipeId)

        _recipe.value?.title = recipe.title
        _recipe.value?.description = recipe.description
        _recipe.value?.recipe = recipe.recipe
        _recipe.value?.categories = recipe.categories
    }

    fun toggleCategoryChecked(category: Category) {
        val updatedCategories = _recipe.value?.categories?.categories?.map {
            if (it == category) {
                it.copy(isChecked = !it.isChecked)
            } else {
                it
            }
        }

        _recipe.value = _recipe.value?.copy(
            categories = Categories(updatedCategories!!)
        )
    }

    fun updateRecipeTitle(title: String) {
        _recipe.value = _recipe.value?.copy(title = title) ?: Recipe(
            title = title,
            description = "",
            recipe = ""
        )
    }

    fun updateDescription(description: String) {
        _recipe.value = _recipe.value?.copy(description = description) ?: Recipe(
            title = "",
            description = description,
            recipe = ""
        )
    }

    fun updateRecipeCook(recipe: String) {
        _recipe.value = _recipe.value?.copy(recipe = recipe) ?: Recipe(
            title = "",
            description = "",
            recipe = recipe
        )
    }

    fun addRecipe() {
        viewModelScope.launch {
            recipeDao.addRecipe(
                Recipe(
                    title = _recipe.value?.title!!,
                    description = _recipe.value?.description!!,
                    recipe = _recipe.value?.recipe!!,
                    categories = _recipe.value?.categories!!
                )
            )
        }
    }

    fun resetFields() {
        _recipe.value = Recipe(
            title = "",
            description = "",
            recipe = "",
            categories = Categories()
        )
    }

    fun deleteRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeDao.deleteTodo(recipeId)
        }
    }

    fun updateRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeDao.updateRecipe(
                Recipe(
                    id = recipeId,
                    title = _recipe.value?.title!!,
                    description = _recipe.value?.description!!,
                    recipe = _recipe.value?.recipe!!,
                    categories = _recipe.value?.categories!!
                )
            )
        }
    }

    suspend fun getRecipe(id: Int): Recipe {
        return recipeDao.getRecipe(id)
    }
}
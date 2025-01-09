package denys.diomaxius.simplerecipe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import denys.diomaxius.simplerecipe.MyApp
import denys.diomaxius.simplerecipe.data.Categories
import denys.diomaxius.simplerecipe.data.Category
import denys.diomaxius.simplerecipe.data.Recipe
import kotlinx.coroutines.launch

class RecipeScreenViewModel : ViewModel() {
    private val recipeDao = MyApp.recipeDatabase.getRecipeDao()

    private val _recipesList = MutableLiveData<List<Recipe>>(emptyList())
    private val _allRecipesList = MutableLiveData<List<Recipe>>(emptyList())
    var recipesList: LiveData<List<Recipe>> = _recipesList

    private val _recipe = MutableLiveData<Recipe>(
        Recipe(
            title = "",
            description = "",
            recipe = "",
            categories = Categories()
        )
    )
    val recipe: LiveData<Recipe> = _recipe

    init {
        loadAllRecipes()
        resetRecipesList()
    }

    private fun resetRecipesList() {
        _allRecipesList.observeForever { allRecipes ->
            if (allRecipes != null) {
                _recipesList.value = allRecipes
            }
        }
    }

    fun loadAllRecipes() {
        recipeDao.getAllRecipes().observeForever {
            _allRecipesList.value = it
        }
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
                _recipe.value!!
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
                _recipe.value!!.copy(id = recipeId)
            )
        }
    }

    fun sortRecipeListByCategory(category: String) {
        resetRecipesList()
        _recipesList.value = _recipesList.value?.filter {recipe ->
            recipe.categories.categories.any { it.name == category && it.isChecked }
        }
    }

    suspend fun loadRecipe(recipeId: Int) {
        val recipe: Recipe = getRecipe(recipeId)
        _recipe.value = recipe
    }

    suspend fun getRecipe(id: Int): Recipe {
        return recipeDao.getRecipe(id)
    }
}
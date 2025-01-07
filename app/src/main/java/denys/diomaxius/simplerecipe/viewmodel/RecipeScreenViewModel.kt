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
    val recipeDao = MyApp.recipeDatabase.getRecipeDao()
    val recipesList: LiveData<List<Recipe>> = recipeDao.getAllRecipes()

    private val _recipeTitle = MutableLiveData("")
    var recipeTitle: LiveData<String> = _recipeTitle

    private val _description = MutableLiveData("")
    val description: LiveData<String> = _description

    private val _recipeCook = MutableLiveData("")
    val recipeCook: LiveData<String> = _recipeCook

    private val _categories = MutableLiveData<Categories>(Categories())
    val categories: LiveData<Categories> = _categories

    suspend fun loadRecipe (recipeId: Int) {
        val recipe : Recipe = getRecipe(recipeId)

        _recipeTitle.value = recipe.title
        _description.value = recipe.description
        _recipeCook.value = recipe.recipe
        _categories.value = recipe.categories
    }

    fun toggleCategoryChecked(category: Category) {
        val updatedCategories = _categories.value?.categories?.map {
            if (it == category) {
                it.copy(isChecked = !it.isChecked)
            } else {
                it
            }
        }
        _categories.value = Categories(updatedCategories!!)
    }
    fun updateRecipeTitle(title: String) {
        _recipeTitle.value = title
    }

    fun updateDescription(description: String) {
        _description.value = description
    }

    fun updateRecipeCook(recipe: String) {
        _recipeCook.value = recipe
    }

    fun addRecipe() {
        viewModelScope.launch {
            recipeDao.addRecipe(
                Recipe(
                    title = _recipeTitle.value!!,
                    description = _description.value!!,
                    recipe = _recipeCook.value!!,
                    categories = _categories.value!!
                )
            )
        }
    }

    fun resetFields() {
        _description.value = ""
        _recipeTitle.value = ""
        _recipeCook.value = ""
        _categories.value = Categories()
    }

    fun deleteRecipe(recipeId: Int) {
        viewModelScope.launch {
            recipeDao.deleteTodo(recipeId)
        }
    }

    suspend fun getRecipe(id: Int): Recipe {
        return recipeDao.getRecipe(id)
    }
}
package denys.diomaxius.simplerecipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import denys.diomaxius.simplerecipe.data.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM RECIPE")
    fun getAllRecipes() : LiveData<List<Recipe>>

    @Insert
    suspend fun addRecipe(recipe: Recipe)
}
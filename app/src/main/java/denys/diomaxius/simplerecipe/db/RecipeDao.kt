package denys.diomaxius.simplerecipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import denys.diomaxius.simplerecipe.data.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM RECIPE")
    fun getAllRecipes() : LiveData<List<Recipe>>

    @Insert
    suspend fun addRecipe(recipe: Recipe)

    @Query("SELECT * FROM RECIPE WHERE id = :recipeId")
    suspend fun getRecipe(recipeId: Int): Recipe

    @Query("DELETE FROM RECIPE WHERE id = :recipeId")
    suspend fun deleteTodo(recipeId: Int)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

}
package denys.diomaxius.simplerecipe.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import denys.diomaxius.simplerecipe.data.Recipe

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(Converters::class)
abstract class RecipeDatabase  : RoomDatabase() {
    companion object {
        const val NAME = "RECIPE_DB"
    }

    abstract fun getRecipeDao(): RecipeDao
}
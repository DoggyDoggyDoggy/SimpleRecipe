package denys.diomaxius.simplerecipe

import android.app.Application
import androidx.room.Room
import denys.diomaxius.simplerecipe.db.RecipeDatabase

class MyApp : Application() {
    companion object {
        lateinit var recipeDatabase: RecipeDatabase
    }

    override fun onCreate() {
        super.onCreate()
        recipeDatabase = Room.databaseBuilder(
            applicationContext,
            RecipeDatabase::class.java,
            RecipeDatabase.NAME
        ).build()
    }
}
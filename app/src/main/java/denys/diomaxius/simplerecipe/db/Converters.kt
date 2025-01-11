package denys.diomaxius.simplerecipe.db

import androidx.room.TypeConverter
import denys.diomaxius.simplerecipe.data.Categories
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromCategories(categories: Categories): String {
        return json.encodeToString(categories)
    }

    @TypeConverter
    fun toCategories(categoriesString: String): Categories {
        return json.decodeFromString(categoriesString)
    }
}
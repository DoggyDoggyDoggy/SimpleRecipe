package denys.diomaxius.simplerecipe.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    var isChecked: Boolean = false
)

@Serializable
data class Categories(
    var categories: List<Category> = listOf(
        Category(name = "Keto"),
        Category(name = "Healthy"),
        Category(name = "Easy to Cook")
    )
)

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var title: String,
    var description: String,
    var recipe: String,
    var categories: Categories = Categories()
)

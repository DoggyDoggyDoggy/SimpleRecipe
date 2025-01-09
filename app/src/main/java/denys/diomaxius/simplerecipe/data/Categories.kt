package denys.diomaxius.simplerecipe.data

import kotlinx.serialization.Serializable

enum class CategoryName(val categoryName: String) {
    Healthy("Healthy"),
    Vegetarian("Vegetarian"),
    EasyToCook("Easy to cook"),
    Keto("Keto")
}

@Serializable
data class Category(
    val name: String,
    var isChecked: Boolean = false
)

@Serializable
data class Categories(
    var categories: List<Category> =
        CategoryName.entries.map { Category(name = it.categoryName) }
)
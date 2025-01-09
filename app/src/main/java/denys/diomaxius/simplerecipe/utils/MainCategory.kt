package denys.diomaxius.simplerecipe.utils

import denys.diomaxius.simplerecipe.data.Category
import denys.diomaxius.simplerecipe.data.CategoryName


fun returnMainCategory(categories: List<Category>): List<String> {
    val validCategories = listOf<String>(
        CategoryName.Healthy.categoryName,
        CategoryName.Vegetarian.categoryName,
        CategoryName.EasyToCook.categoryName
    )

    val categories = categories.filter { it.isChecked }.map { it.name }

    if (categories.isEmpty()) return listOf()

    val result = mutableListOf<String>()
    result.addAll(categories.filter { it in validCategories })

    val categoriesLeft = categories.toMutableList()
    categoriesLeft.removeAll(result)

    while (result.size<3 && categoriesLeft.isNotEmpty()) {
        val randomCategory = categoriesLeft.random()
        result.add(randomCategory)
        categoriesLeft.remove(randomCategory)
    }

    return result
}
package denys.diomaxius.simplerecipe.utils

import denys.diomaxius.simplerecipe.data.Category


fun returnMainCategory(categories: List<Category>): List<String> {
    val validCategories = listOf<String>("Healthy", "Easy to cook", "Vegetarian")

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
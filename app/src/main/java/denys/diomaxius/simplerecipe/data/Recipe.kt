package denys.diomaxius.simplerecipe.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var description: String,
    var recipe: String,
    var categories: Categories = Categories()
)

package denys.diomaxius.simplerecipe.ui.screens.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import denys.diomaxius.simplerecipe.R

@Composable
fun TopBar(
    prevPage: () -> Unit,
    deleteRecipe: () -> Unit,
    editRecipe: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = prevPage) {
            Icon(
                tint = MaterialTheme.colorScheme.onSecondary,
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.go_back)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = editRecipe) {
            Icon(
                tint = MaterialTheme.colorScheme.onSecondary,
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(R.string.edit)
            )
        }

        IconButton(
            onClick = { showDeleteDialog = true }
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.onSecondary,
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete)
            )
        }

        DeleteRecipeDialog(
            showDeleteDialog = showDeleteDialog,
            dismiss = { showDeleteDialog = false },
            deleteRecipe = deleteRecipe,
            prevPage = prevPage
        )
    }
}
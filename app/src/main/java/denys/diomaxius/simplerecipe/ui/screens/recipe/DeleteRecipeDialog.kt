package denys.diomaxius.simplerecipe.ui.screens.recipe

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import denys.diomaxius.simplerecipe.R

@Composable
fun DeleteRecipeDialog(
    dismiss: () -> Unit,
    showDeleteDialog: Boolean,
    deleteRecipe: () -> Unit,
    prevPage: () -> Unit
) {
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = dismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        deleteRecipe()
                        prevPage()
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = dismiss
                ) {
                    Text(stringResource(R.string.dismiss))
                }
            },
            title = {
                Text(text = stringResource(R.string.delete_recipe))
            },
            text = {
                Text(text = stringResource(R.string.are_you_sure_you_want_to_delete_recipe))
            }
        )
    }
}

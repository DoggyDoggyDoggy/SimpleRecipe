package denys.diomaxius.simplerecipe.ui.screens.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import denys.diomaxius.simplerecipe.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(drawerState: DrawerState, scope: CoroutineScope, categoryName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2f5233)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply { if (isClosed) open() else close() }
                }
            }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        }
        Text(
            text = categoryName,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
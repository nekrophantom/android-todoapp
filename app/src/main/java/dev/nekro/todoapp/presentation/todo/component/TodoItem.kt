package dev.nekro.todoapp.presentation.todo.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.nekro.todoapp.domain.models.Todo

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onCheckboxClicked: (Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(todo.isCompleted) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() }
    ) {
        Row (
            modifier = modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = todo.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None
            )

            Checkbox(
                checked = isChecked,
                onCheckedChange = { newCheckedState  ->
                    onCheckboxClicked(newCheckedState)
                    isChecked = newCheckedState
                }
            )
        }
    }

}
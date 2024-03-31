package dev.nekro.todoapp.presentation.add_edit_todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.nekro.todoapp.core.components.AppBar
import dev.nekro.todoapp.domain.models.Todo
import kotlinx.coroutines.launch

@Composable
fun AddEditTodoScreen(
    id: Long,
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()

    if (id != 0L) {
        val todo = viewModel.getTodoById(id).collectAsState(initial = Todo("", "", false, 0L))
        viewModel.onTodoTitleChange(todo.value.title)
        viewModel.onTodoContentChange(todo.value.content)
    } else {
        viewModel.onTodoTitleChange("")
        viewModel.onTodoContentChange("")
    }

    Scaffold (
        topBar = {
            AppBar(title = if (id != 0L) "Edit Todo" else "Add New Todo") {
                navController.navigateUp()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.insertTodo(
                        Todo(
                            id = id,
                            title = viewModel.todoTitleState.value.trim(),
                            content = viewModel.todoContentState.value.trim()
                        )
                    )
                    navController.navigateUp()
                }
            ) {
                Icon(imageVector = Icons.Default.Create, contentDescription = "Add")
            }
        }
    ) { scaffoldPadding ->
        Column (
            modifier = Modifier
                .wrapContentSize()
                .padding(scaffoldPadding)
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.todoTitleState.value,
                onValueChange = {
                    viewModel.onTodoTitleChange(it)
                },
                label = {
                    Text(text = "Title")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                value = viewModel.todoContentState.value,
                onValueChange = {
                    viewModel.onTodoContentChange(it)
                },
                label = {
                    Text(text = "Content")
                }
            )

        }
    }

}

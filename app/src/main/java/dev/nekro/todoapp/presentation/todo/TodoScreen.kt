package dev.nekro.todoapp.presentation.todo

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.nekro.todoapp.core.components.AppBar
import dev.nekro.todoapp.domain.util.Screen
import dev.nekro.todoapp.presentation.add_edit_todo.AddEditTodoViewModel
import dev.nekro.todoapp.presentation.todo.component.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    navController: NavController,
    viewModel: TodoViewModel = hiltViewModel(),
    addEditViewModel: AddEditTodoViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            AppBar(
                title = "Todo App"
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditTodoScreen.route + "/0L")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {

        LazyColumn (
            modifier = Modifier.padding(it)
        ) {
            items(viewModel.state.value.todos, key = { todo -> todo.id }) { todo ->

                val dismissState = rememberDismissState(
                    confirmValueChange = { dismiss ->
                        if (dismiss == DismissValue.DismissedToStart) {
                            viewModel.deleteTodo(todo)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if (dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                            label = ""
                        )
                        
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp)
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    },
                    dismissContent = {
                        TodoItem(
                            todo = todo,
                            onClick = {
                                val id = todo.id
                                navController.navigate(Screen.AddEditTodoScreen.route + "/$id")
                            },
                            onCheckboxClicked = { isChecked ->
                                addEditViewModel.insertTodo(todo.copy(isCompleted = isChecked))
                            }
                        )
                    }
                )

            }
        }
    }

}
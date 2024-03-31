package dev.nekro.todoapp.presentation.todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nekro.todoapp.domain.models.Todo
import dev.nekro.todoapp.domain.use_case.TodoUseCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoUseCase : TodoUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TodoState())
    val state: State<TodoState> = _state

    private lateinit var getAllTodos: Flow<List<Todo>>

    init {
        viewModelScope.launch {
            getAllTodos = todoUseCase.getAllTodos()
            getAllTodos.collect { todos ->
                _state.value = TodoState(todos = todos)
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoUseCase.deleteTodo(todo)
        }
    }
}
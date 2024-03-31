package dev.nekro.todoapp.presentation.add_edit_todo

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
class AddEditTodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCases
) : ViewModel() {

    private val _todoTitleState = mutableStateOf("")
    val todoTitleState: State<String> = _todoTitleState

    private val _todoContentState = mutableStateOf("")
    val todoContentState: State<String> = _todoContentState

    fun onTodoTitleChange(newString: String) {
        _todoTitleState.value = newString
    }

    fun onTodoContentChange(newString: String) {
        _todoContentState.value = newString
    }

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            todoUseCase.insertTodo(todo)
        }
    }

    fun getTodoById(id: Long): Flow<Todo> {
        return todoUseCase.getTodo(id)
    }

}
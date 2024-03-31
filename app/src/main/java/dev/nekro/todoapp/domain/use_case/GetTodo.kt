package dev.nekro.todoapp.domain.use_case

import dev.nekro.todoapp.domain.models.Todo
import dev.nekro.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodo(private val repository: TodoRepository) {
    operator fun invoke(id: Long): Flow<Todo> {
        return repository.getTodoById(id)
    }
}
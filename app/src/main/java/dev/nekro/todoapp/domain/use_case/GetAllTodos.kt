package dev.nekro.todoapp.domain.use_case

import dev.nekro.todoapp.domain.models.Todo
import dev.nekro.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetAllTodos(private val repository: TodoRepository) {
    operator fun invoke(): Flow<List<Todo>> {
        return repository.getAllTodos()
    }
}
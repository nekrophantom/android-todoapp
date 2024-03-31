package dev.nekro.todoapp.domain.use_case

import dev.nekro.todoapp.domain.models.Todo
import dev.nekro.todoapp.domain.repository.TodoRepository

class InsertTodo(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) {
        return repository.insertTodo(todo)
    }
}
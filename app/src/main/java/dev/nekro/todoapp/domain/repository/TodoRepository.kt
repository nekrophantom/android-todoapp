package dev.nekro.todoapp.domain.repository

import dev.nekro.todoapp.domain.models.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getAllTodos(): Flow<List<Todo>>

    fun getTodoById(id: Long) : Flow<Todo>

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

}
package dev.nekro.todoapp.data.repository

import dev.nekro.todoapp.data.data_source.TodoDao
import dev.nekro.todoapp.domain.models.Todo
import dev.nekro.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val todoDao: TodoDao) : TodoRepository {
    override fun getAllTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos()
    }

    override fun getTodoById(id: Long): Flow<Todo> {
        return todoDao.getTodoById(id)
    }

    override suspend fun insertTodo(todo: Todo) {
        return todoDao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        return todoDao.deleteTodo(todo)
    }
}
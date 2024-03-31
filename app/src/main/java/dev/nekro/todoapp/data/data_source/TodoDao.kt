package dev.nekro.todoapp.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.nekro.todoapp.domain.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("Select * from todo")
    fun getAllTodos(): Flow<List<Todo>>

    @Query("Select * from todo where id = :id")
    fun getTodoById(id: Long): Flow<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

}
package dev.nekro.todoapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nekro.todoapp.data.data_source.TodoDao
import dev.nekro.todoapp.data.data_source.TodoDatabase
import dev.nekro.todoapp.data.repository.TodoRepositoryImpl
import dev.nekro.todoapp.domain.repository.TodoRepository
import dev.nekro.todoapp.domain.use_case.DeleteTodo
import dev.nekro.todoapp.domain.use_case.GetAllTodos
import dev.nekro.todoapp.domain.use_case.GetTodo
import dev.nekro.todoapp.domain.use_case.InsertTodo
import dev.nekro.todoapp.domain.use_case.TodoUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    
    @Provides
    @Singleton
    fun provideTodoDatabase(application: Application) : TodoDatabase {
        return Room.databaseBuilder(application, TodoDatabase::class.java, TodoDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: TodoDatabase) : TodoDao {
        return database.tododao()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoDao: TodoDao) : TodoRepository {
        return TodoRepositoryImpl(todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoUseCases(repository: TodoRepository) : TodoUseCases {
        return TodoUseCases(
            getTodo = GetTodo(repository),
            getAllTodos = GetAllTodos(repository),
            insertTodo = InsertTodo(repository),
            deleteTodo = DeleteTodo(repository)
        )
    }

}
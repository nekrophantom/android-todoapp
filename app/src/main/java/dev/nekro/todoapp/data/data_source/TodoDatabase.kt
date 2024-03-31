package dev.nekro.todoapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.nekro.todoapp.domain.models.Todo

@Database(
    entities = [Todo::class],
    version = 5
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun tododao() : TodoDao

    companion object{
        const val DATABASE_NAME = "tododb"
    }
}
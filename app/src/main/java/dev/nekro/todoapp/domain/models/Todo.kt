package dev.nekro.todoapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val content: String,
    val isCompleted: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L
)

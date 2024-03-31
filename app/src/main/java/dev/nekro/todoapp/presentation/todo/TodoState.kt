package dev.nekro.todoapp.presentation.todo

import dev.nekro.todoapp.domain.models.Todo

data class TodoState (
    val todos: List<Todo> = emptyList()
)
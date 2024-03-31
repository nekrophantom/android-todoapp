package dev.nekro.todoapp.domain.use_case

data class TodoUseCases(
    val getAllTodos: GetAllTodos,
    val getTodo: GetTodo,
    val insertTodo: InsertTodo,
    val deleteTodo: DeleteTodo
)

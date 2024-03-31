package dev.nekro.todoapp.domain.util

sealed class Screen (val route: String) {
    object TodoScreen : Screen("home_screen")
    object AddEditTodoScreen : Screen("add_edit_screen")
}
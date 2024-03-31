package dev.nekro.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.nekro.todoapp.domain.util.Screen
import dev.nekro.todoapp.presentation.add_edit_todo.AddEditTodoScreen
import dev.nekro.todoapp.presentation.todo.TodoScreen
import dev.nekro.todoapp.ui.theme.TodoAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screen.TodoScreen.route) {

                        composable(Screen.TodoScreen.route) {
                            TodoScreen(navController = navController)
                        }

                        composable(Screen.AddEditTodoScreen.route + "/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.LongType
                                    defaultValue = 0L
                                }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getLong("id") ?: 0L
                            AddEditTodoScreen(id = id, navController = navController)
                        }

                    }

                }
            }
        }
    }
}
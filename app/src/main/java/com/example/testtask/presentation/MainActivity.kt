package com.example.testtask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testtask.common.Routes
import com.example.testtask.presentation.theme.ComposeMaterial3Theme
import com.example.testtask.presentation.ui.repos.ReposScreen
import com.example.testtask.presentation.ui.users.UsersScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMaterial3Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.USERS.route) {
                    composable(Routes.USERS.route) {
                        UsersScreen(
                            modifier = Modifier.fillMaxSize(),
                            navController
                        )
                    }
                    composable(
                        "${Routes.REPOS.route}?username={username}?ownerId={ownerId}",
                        arguments =
                        listOf(
                            navArgument("username") { type = NavType.StringType },
                            navArgument("ownerId") { type = NavType.IntType }
                        )
                    ) {
                        val username = it.arguments?.getString("username")
                        val ownerId = it.arguments?.getInt("ownerId")
                        if (username != null && ownerId != null) {
                            ReposScreen(
                                modifier = Modifier.fillMaxSize(),
                                username = username,
                                ownerId = ownerId
                            )
                        }
                    }
                }
            }
        }
    }


}


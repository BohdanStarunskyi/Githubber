package com.example.testtask.presentation.ui.users

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testtask.common.Routes
import com.example.testtask.presentation.components.UserItem

@Composable
fun UsersScreen(modifier: Modifier, viewModel: UsersViewModel?, navController: NavController) {
    LaunchedEffect(Unit) {
        viewModel?.getUsersFromDatabase()
        viewModel?.getUsersFromServer()
    }
    when (val state = viewModel?.usersState?.collectAsState()?.value) {
        is UsersStates.Loading -> {}
        is UsersStates.Success -> {
            val users = state.users
            if (users.isNotEmpty()){
                LazyColumn(modifier) {
                    items(users.size) {
                        val user = users[it]
                        UserItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            user = user
                        ) {
                            runCatching {
                                navController.navigate(
                                    "${Routes.REPOS.route}?username=${user.username}?ownerId=${user.id}"
                                )
                            }
                        }
                    }
                }
            }
        }
        is UsersStates.Error -> {
            Log.e("USERS", state.error.localizedMessage?.toString() ?: "")
        }
        else -> {}
    }

}

@Composable
@Preview(showBackground = true)
fun ReposScreenPreview() {
    UsersScreen(Modifier.fillMaxSize(), null, rememberNavController())
}
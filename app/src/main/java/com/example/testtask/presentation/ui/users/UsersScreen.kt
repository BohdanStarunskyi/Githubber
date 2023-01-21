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
import com.example.testtask.presentation.components.UserItem

@Composable
fun UsersScreen(modifier: Modifier, viewModel: UsersViewModel?) {
    LaunchedEffect(Unit) {
        viewModel?.getUsersFromDatabase()
        viewModel?.getUsersFromServer()
    }
    when (val state = viewModel?.usersState?.collectAsState()?.value) {
        is UsersStates.Loading -> {
        }
        is UsersStates.Success -> {
            LazyColumn(modifier) {
                val users = state.users
                items(users.size) {
                    UserItem(modifier = Modifier.fillMaxWidth().padding(5.dp), users[it]) {}
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
    UsersScreen(Modifier.fillMaxSize(), null)
}
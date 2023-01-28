package com.example.testtask.presentation.ui.users

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testtask.R
import com.example.testtask.common.Routes
import com.example.testtask.presentation.components.UserItem
import com.example.testtask.presentation.ui.UiStates

@Composable
fun UsersScreen(modifier: Modifier, navController: NavController) {
    val viewModel: UsersViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getUsersFromDatabase()
        viewModel.getUsersFromServer()
    }
    val shouldBeVisible = remember { mutableStateOf(false) }
    val users = remember { viewModel.userList }
    when (val state = viewModel.usersState.collectAsState().value) {
        is UiStates.Loading -> {
            if (users.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        Modifier
                            .size(120.dp)
                            .align(Alignment.Center)
                    )
                }
                shouldBeVisible.value = false
            }
        }
        is UiStates.Success -> {
            shouldBeVisible.value = true
        }
        is UiStates.Error -> {
            if (users.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(R.string.check_your_internet_connection),
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
                shouldBeVisible.value = false
            }
            Log.e("USERS", state.error.localizedMessage?.toString() ?: "")
        }
        else -> {}
    }
    if (shouldBeVisible.value) {
        LazyColumn(modifier) {
            items(users.size) {
                runCatching {
                    val user = users[it]
                    UserItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        user = user
                    ) {
                        navController.navigate(
                            "${Routes.REPOS.route}?username=${user.username}?ownerId=${user.id}"
                        )
                    }
                }

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReposScreenPreview() {
    UsersScreen(Modifier.fillMaxSize(), rememberNavController())
}
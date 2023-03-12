package com.example.testtask.presentation.ui.users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testtask.R
import com.example.testtask.common.constants.Routes
import com.example.testtask.common.testing.getFakeUsers
import com.example.testtask.presentation.components.UserItem

@Composable
fun UsersScreen(modifier: Modifier, navController: NavController) {
    val viewModel: UsersViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getUsersFromDatabase()
        viewModel.getUsersFromServer()
    }
    val state = viewModel.usersState.value
    UsersScreenContent(
        modifier = modifier,
        state = state,
        onUserClicked = { username, id ->
            runCatching {
                navController.navigate("${Routes.REPOS.route}?username=${username}?ownerId=${id}")
            }
        }
    )
}

@Composable
fun UsersScreenContent(
    modifier: Modifier,
    state: UsersState,
    onUserClicked: (username: String, id: Int) -> Unit
) {
    if (state.isLoading && state.users.isNullOrEmpty()) {
        Box(modifier) {
            CircularProgressIndicator(
                Modifier
                    .size(120.dp)
                    .align(Alignment.Center)
            )
        }
    }
    if (!state.error.isNullOrEmpty() && state.users.isNullOrEmpty()) {
        Box(modifier) {
            Text(
                text = stringResource(R.string.check_your_internet_connection),
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    LazyColumn(modifier) {
        items(state.users ?: listOf()) { user ->
            UserItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                user = user
            ) {
                onUserClicked(user.username ?: "", user.id ?: 0)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UsersScreenPreview() {
    UsersScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = UsersState(users = getFakeUsers(10)),
        onUserClicked = { _, _ -> }
    )
}
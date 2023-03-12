package com.example.testtask.presentation.ui.repos

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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testtask.R
import com.example.testtask.common.testing.getFakeRepos
import com.example.testtask.presentation.components.RepositoryItem

@Composable
fun ReposScreen(modifier: Modifier, username: String, ownerId: Int) {
    val viewModel: ReposViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getReposFromDatabase(ownerId)
        viewModel.getReposFromServer(username, ownerId)
    }
    val state = viewModel.reposState.value
    val uriHandler = LocalUriHandler.current
    ReposScreenContent(
        modifier = modifier,
        state = state,
        onRepoClick = { uriHandler.openUri(it) }
    )
}

@Composable
fun ReposScreenContent(
    modifier: Modifier,
    state: ReposState,
    onRepoClick: (url: String) -> Unit
) {
    if (state.isLoading && state.repos.isNullOrEmpty()) {
        Box(modifier) {
            CircularProgressIndicator(
                Modifier
                    .size(120.dp)
                    .align(Alignment.Center)
            )
        }
    }

    if (!state.error.isNullOrEmpty() && state.repos.isNullOrEmpty()) {
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
        items(state.repos ?: listOf()) { repo ->
            runCatching {
                RepositoryItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    repositoryEntity = repo
                ) {
                    repo.repoUrl?.let(onRepoClick)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReposScreenPreview() {
    ReposScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = ReposState(repos = getFakeRepos(10)),
        onRepoClick = {}
    )
}
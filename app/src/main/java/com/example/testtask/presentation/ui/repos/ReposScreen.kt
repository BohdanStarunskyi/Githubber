package com.example.testtask.presentation.ui.repos

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.example.testtask.presentation.components.RepositoryItem
import com.example.testtask.presentation.ui.UiStates

@Composable
fun ReposScreen(modifier: Modifier, username: String, ownerId: Int) {
    val viewModel: ReposViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getReposFromDatabase(ownerId)
        viewModel.getReposFromServer(username, ownerId)
    }
    val shouldBeVisible = remember { mutableStateOf(false) }
    val repos = remember { viewModel.reposList }
    when (val state = viewModel.reposState.collectAsState().value) {
        is UiStates.Loading -> {
            if (repos.isEmpty()) {
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
            if (repos.isEmpty()) {
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
            Log.e("REPOS", state.error.localizedMessage?.toString() ?: "")
        }
        else -> {}

    }

    val uriHandler = LocalUriHandler.current
    LazyColumn(modifier) {
        items(repos.size ) {
            runCatching {
                val repo = repos[it]
                RepositoryItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    repositoryEntity = repo
                ) {
                    uriHandler.openUri(repo.repoUrl ?: "")
                }

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ReposScreenPreview() {
    ReposScreen(Modifier.fillMaxSize(), "", 0)
}
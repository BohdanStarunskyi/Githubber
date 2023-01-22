package com.example.testtask.presentation.ui.repos

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
import com.example.testtask.presentation.components.RepositoryItem

@Composable
fun ReposScreen(modifier: Modifier, viewModel: ReposViewModel?, username: String, ownerId: Int) {
    LaunchedEffect(Unit) {
        viewModel?.getReposFromDatabase(ownerId)
        viewModel?.getReposFromServer(username, ownerId)
    }
    when (val state = viewModel?.reposState?.collectAsState()?.value) {
        is ReposStates.Loading -> {}
        is ReposStates.Success -> {
            val repos = state.repos
            if (repos.isNotEmpty()){
                LazyColumn(modifier) {
                    items(repos.size) {
                        RepositoryItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp), repos[it]
                        ) {}
                    }
                }
            }
        }
        is ReposStates.Error -> {
            Log.e("REPOS", state.error.localizedMessage?.toString() ?: "")
        }
        else -> {}

    }
}

@Composable
@Preview(showBackground = true)
fun ReposScreenPreview() {
    ReposScreen(Modifier.fillMaxSize(), null, "", 0)
}
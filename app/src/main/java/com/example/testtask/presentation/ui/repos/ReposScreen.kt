package com.example.testtask.presentation.ui.repos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.testtask.common.Response
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.presentation.components.RepositoryItem

@Composable
fun ReposScreen(
    modifier: Modifier,
    viewModel: ReposViewModel = hiltViewModel()
) {
    val uriHandler = LocalUriHandler.current
    ReposScreenContent(
        modifier = modifier,
        state = viewModel.reposState.collectAsState().value,
        onRepoClick = { uriHandler.openUri(it) }
    )
}

@Composable
fun ReposScreenContent(
    modifier: Modifier,
    state: Response<List<RepositoryEntity>>,
    onRepoClick: (url: String) -> Unit
) {
    when (state) {
        is Response.Loading -> {
            Box(modifier) {
                CircularProgressIndicator(
                    Modifier
                        .size(120.dp)
                        .align(Alignment.Center)
                )
            }
        }

        is Response.Error -> {
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

        is Response.Success -> {
            LazyColumn(modifier) {
                items(state.data) { repo ->
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
    }
}

@Composable
@Preview(showBackground = true)
fun ReposScreenPreview() {
    ReposScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = Response.Loading(),
        onRepoClick = {}
    )
}
package com.example.testtask.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testtask.R
import com.example.testtask.domain.entities.RepositoryEntity

@Composable
fun RepositoryItem(modifier: Modifier, repositoryEntity: RepositoryEntity, onClick: () -> Unit) {
    Card(modifier.clickable { onClick() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(
                text = repositoryEntity.updatedAt.toString(),
                modifier = Modifier.align(Alignment.End)
            )
            Text(
                text = repositoryEntity.name.toString(),
                fontSize = 36.sp,
                fontWeight = FontWeight(800),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_programming_language),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = repositoryEntity.language.toString(),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = repositoryEntity.stargazersCount.toString(),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RepositoryItemPreview() {
    val repo = RepositoryEntity(0, "repo", "yesterday", 10, "Kotlin", "")
    RepositoryItem(
        modifier = Modifier.fillMaxWidth(),
        repositoryEntity = repo
    ) {}
}
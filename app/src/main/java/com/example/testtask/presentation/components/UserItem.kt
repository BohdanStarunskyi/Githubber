package com.example.testtask.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.testtask.R
import com.example.testtask.domain.entities.UserEntity

@Composable
fun UserItem(modifier: Modifier, user: UserEntity, onClick: () -> Unit) {
    Card(modifier.clickable { onClick() }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = rememberAsyncImagePainter(user.avatarUrl),
                contentDescription = stringResource(
                    R.string.user_image_desc
                )
            )
            Text(
                text = user.username.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserItemPreview() {
    val user = UserEntity(id = 0, username = "jake", avatarUrl = "https://i.imgur.com/Di7CszA.jpeg")
    UserItem(Modifier.fillMaxWidth(), user) {}
}
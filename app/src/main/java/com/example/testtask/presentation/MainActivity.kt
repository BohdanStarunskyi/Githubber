package com.example.testtask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testtask.presentation.ui.users.UsersScreen
import com.example.testtask.presentation.ui.users.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: UsersViewModel = hiltViewModel()
            UsersScreen(modifier = Modifier.fillMaxSize(), viewModel = viewModel)
        }
    }


}


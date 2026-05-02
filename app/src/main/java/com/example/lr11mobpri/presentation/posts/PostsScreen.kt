package com.example.lr11mobpri.presentation.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PostsScreen(viewModel: PostsViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Загружаем данные при первом появлении
    LaunchedEffect(Unit) {
        viewModel.loadPosts()
    }

    // Обработка ошибок – можно показать Snackbar, но для простоты – текст
    if (uiState.error != null) {
        LaunchedEffect(uiState.error) {
            // Здесь можно показать Snackbar, но для лабораторной достаточно текста
        }
    }

    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        uiState.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Ошибка: ${uiState.error}", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadPosts() }) {
                        Text("Повторить")
                    }
                }
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.posts, key = { it.id }) { post ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = post.title, style = MaterialTheme.typography.titleMedium)
                            Text(text = post.body, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
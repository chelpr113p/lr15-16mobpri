package com.example.lr11mobpri.presentation.posts

import com.example.lr11mobpri.domain.model.Post

data class PostsUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

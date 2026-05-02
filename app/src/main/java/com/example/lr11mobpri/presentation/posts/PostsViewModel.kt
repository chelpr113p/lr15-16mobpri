package com.example.lr11mobpri.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lr11mobpri.domain.model.Post
import com.example.lr11mobpri.domain.usecase.AddPostUseCase
import com.example.lr11mobpri.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val addPostUseCase: AddPostUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostsUiState())
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

    fun loadPosts(page: Int = 1, limit: Int = 20) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getPostsUseCase(page, limit)
                .onSuccess { posts ->
                    _uiState.update { it.copy(posts = posts, isLoading = false, error = null) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message ?: "Ошибка загрузки") }
                }
        }
    }

    fun addPost(title: String, body: String) {
        if (title.isBlank()) return
        val newPost = Post(id = 0, userId = 1, title = title, body = body) // id=0 – сервер назначит
        viewModelScope.launch {
            addPostUseCase(newPost)
                .onSuccess {
                    loadPosts() // перезагружаем список
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message ?: "Ошибка добавления") }
                }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
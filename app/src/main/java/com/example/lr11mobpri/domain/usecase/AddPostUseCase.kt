package com.example.lr11mobpri.domain.usecase

import com.example.lr11mobpri.domain.model.Post
import com.example.lr11mobpri.domain.repository.PostRepository

class AddPostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(post: Post): Result<Unit> {
        if (post.title.isBlank()) {
            return Result.failure(IllegalArgumentException("Заголовок не может быть пустым"))
        }
        return repository.addPost(post)
    }
}
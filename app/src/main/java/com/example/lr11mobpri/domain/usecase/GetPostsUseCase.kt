package com.example.lr11mobpri.domain.usecase

import com.example.lr11mobpri.domain.model.Post
import com.example.lr11mobpri.domain.repository.PostRepository

class GetPostsUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(page: Int = 1, limit: Int = 20): Result<List<Post>> =
        repository.getPosts(page, limit)
}
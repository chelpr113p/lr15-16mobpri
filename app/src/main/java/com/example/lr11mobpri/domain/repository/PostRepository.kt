package com.example.lr11mobpri.domain.repository

import com.example.lr11mobpri.domain.model.Post

interface PostRepository {
    suspend fun getPosts(page: Int, limit: Int): Result<List<Post>>
    suspend fun addPost(post: Post): Result<Unit>
}
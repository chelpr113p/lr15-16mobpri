package com.example.lr11mobpri.data.repository

import com.example.lr11mobpri.data.remote.api.PostApi
import com.example.lr11mobpri.data.remote.mapper.toDomain
import com.example.lr11mobpri.data.remote.mapper.toDto
import com.example.lr11mobpri.domain.model.Post
import com.example.lr11mobpri.domain.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {

    override suspend fun getPosts(page: Int, limit: Int): Result<List<Post>> =
        withContext(Dispatchers.IO) {
            try {
                val postsDto = api.getPosts(page, limit)
                Result.success(postsDto.map { it.toDomain() })
            } catch (e: IOException) {
                Result.failure(e)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun addPost(post: Post): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                api.addPost(post.toDto())
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}
package com.example.lr11mobpri.data.remote.api

import com.example.lr11mobpri.data.remote.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostApi {
    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<PostDto>

    @POST("posts")
    suspend fun addPost(@retrofit2.http.Body post: PostDto): PostDto
}
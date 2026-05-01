package com.example.lr11mobpri.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lr11mobpri.data.model.Post
import com.example.lr11mobpri.data.remote.PostPagingSource
import com.example.lr11mobpri.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow

object PostPagingProvider {
    private val api = RetrofitClient.postApi

    fun getPostsFlow(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            pagingSourceFactory = { PostPagingSource(api) }
        ).flow
    }
}
package com.example.lr11mobpri.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lr11mobpri.data.model.Post

class PostPagingSource(
    private val api: PostApi
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val page = params.key ?: 1
            val limit = params.loadSize

            Log.d("PostPaging", "Загрузка страницы $page (размер $limit)")

            val response = api.getPosts(page = page, limit = limit)

            Log.d("PostPaging", "Страница $page загружена: ${response.size} постов")

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.size == limit) page + 1 else null
            )
        } catch (e: Exception) {
            Log.e("PostPaging", "Ошибка загрузки страницы", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        // Определяем ключ для обновления после инвалидации данных
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}
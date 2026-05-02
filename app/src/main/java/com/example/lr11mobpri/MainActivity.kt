package com.example.lr11mobpri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lr11mobpri.data.remote.api.PostApi
import com.example.lr11mobpri.data.repository.PostRepositoryImpl
import com.example.lr11mobpri.domain.usecase.AddPostUseCase
import com.example.lr11mobpri.domain.usecase.GetPostsUseCase
import com.example.lr11mobpri.presentation.posts.PostsScreen
import com.example.lr11mobpri.presentation.posts.PostsViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Создаём Retrofit и API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val postApi = retrofit.create(PostApi::class.java)

        // 2. Создаём репозиторий
        val postRepository = PostRepositoryImpl(postApi)

        // 3. Создаём UseCase
        val getPostsUseCase = GetPostsUseCase(postRepository)
        val addPostUseCase = AddPostUseCase(postRepository)

        // 4. Создаём ViewModel (через фабрику, чтобы сохранить при повороте)
        val viewModel = PostsViewModel(getPostsUseCase, addPostUseCase)

        setContent {
            PostsScreen(viewModel = viewModel)
        }
    }
}
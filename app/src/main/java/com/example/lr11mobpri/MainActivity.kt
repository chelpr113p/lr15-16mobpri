package com.example.lr11mobpri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lr11mobpri.ui.screens.PostListScreen
import com.example.lr11mobpri.ui.screens.PostListPagingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostListPagingScreen()
        }
    }
}
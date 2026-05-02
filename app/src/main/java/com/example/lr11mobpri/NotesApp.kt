package com.example.lr11mobpri

import android.app.Application
import com.example.lr11mobpri.data.local.AppDatabase

class NotesApp : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }
}
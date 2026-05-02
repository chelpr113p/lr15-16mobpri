package com.example.lr11mobpri.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("id") val id: Long,
    @SerializedName("userId") val userId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)
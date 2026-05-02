package com.example.lr11mobpri.data.remote.mapper

import com.example.lr11mobpri.data.remote.dto.PostDto
import com.example.lr11mobpri.domain.model.Post

fun PostDto.toDomain(): Post = Post(
    id = id,
    userId = userId,
    title = title,
    body = body
)

fun Post.toDto(): PostDto = PostDto(
    id = id,
    userId = userId,
    title = title,
    body = body
)
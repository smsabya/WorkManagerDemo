package com.example.workmanagerdemo.data.network

import com.example.workmanagerdemo.data.model.Comment
import com.example.workmanagerdemo.data.model.Post
import com.example.workmanagerdemo.data.model.User
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit API Service
interface ApiService {
    @GET("posts/1")
    suspend fun getPost(): Post

    @GET("users/1")
    suspend fun getUser(): User

    @GET("comments")
    suspend fun getComments(@Query("postId") postId: Int): List<Comment>
}
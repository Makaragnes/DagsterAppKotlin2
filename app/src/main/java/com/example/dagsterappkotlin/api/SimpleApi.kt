package com.example.dagsterappkotlin.api

import com.example.dagsterappkotlin.model.Post
import retrofit2.http.GET


interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost(): Post
}
package com.example.dagsterappkotlin.repository

import com.example.dagsterappkotlin.api.RetrofitInstance
import com.example.dagsterappkotlin.model.Post


class Repository {

    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }
}
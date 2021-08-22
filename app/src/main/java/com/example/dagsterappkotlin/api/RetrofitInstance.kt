package com.example.dagsterappkotlin.api

import com.example.dagsterappkotlin.model.util.Dynamics.Companion.base_url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)

    }
}
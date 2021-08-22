package com.example.dagsterappkotlin.model

import com.google.gson.annotations.SerializedName

data class Post(

    @SerializedName("flag")
    val flag: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("ip")
    val ip: String
)



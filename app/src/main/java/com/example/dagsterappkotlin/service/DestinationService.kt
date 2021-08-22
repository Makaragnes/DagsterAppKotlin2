package com.example.dagsterappkotlin.service

import com.example.dagsterappkotlin.model.Destination
import com.example.dagsterappkotlin.model.authDestination
import retrofit2.http.*
import retrofit2.Call


interface DestinationService {

    @GET("destination")
    fun getDestinationList(@QueryMap filter: HashMap<String, String>): Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    @POST("/register")
    fun addDestination(@Body newDestination: Destination): Call<Destination>

    @POST("/login")
    fun authDestination(@Body newAuthDestination: authDestination): Call<authDestination>

    @FormUrlEncoded
    @PUT("destination/{id}")
    fun updateDestination(
        @Path("id") id: Int,
        @Field("city") city: String,
        @Field("description") desc: String,
        @Field("country") country: String
    ): Call<Destination>

    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<Unit>
}
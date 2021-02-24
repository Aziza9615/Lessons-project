package com.example.firstapp.network

import com.example.firstapp.model.Publication
import retrofit2.Call
import retrofit2.http.GET

interface SimpleApi {
    @GET("raw/b9416a28938821905dd3b3739bdb2942dc20dc89/Publication.json")
    fun fetchPublications(): Call<MutableList<Publication>>

    @GET("raw/b9416a28938821905dd3b3739bdb2942dc20dc89/Profile.json")
    fun fetchProfile(): Call<Publication>
}

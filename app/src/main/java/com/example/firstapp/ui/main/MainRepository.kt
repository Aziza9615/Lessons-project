package com.example.firstapp.ui.main

import com.example.firstapp.data.App.Companion.getDatabase
import com.example.firstapp.model.Publication
import com.example.firstapp.network.RetrofitClient
import com.example.firstapp.ui.publication.RequestResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val callback: RequestResult) {

    private var api = RetrofitClient().simpleApi

    private val database = getDatabase().instagramDao()

    fun fetchPublications() {
        callback.onSuccess(database.fetchPublications())
        api.fetchPublications().enqueue(object : Callback<MutableList<Publication>> {
            override fun onFailure(call: Call<MutableList<Publication>>, t: Throwable) {
                return callback.onFailure(t)
            }

            override fun onResponse(
                call: Call<MutableList<Publication>>,
                response: Response<MutableList<Publication>>
            ) {
                return if (response.body() != null) {
                    val data = response.body()
                    database.InsertPublications(data)
                    callback.onSuccess(data)
                } else {
                    callback.onFailure(Throwable("error"))
                }
            }
        })
    }

    fun fetchProfile() {
        api.fetchProfile().enqueue(object : Callback<Publication> {
            override fun onFailure(call: Call<Publication>, t: Throwable) {
                return callback.onFailure(t)
            }

            override fun onResponse(call: Call<Publication>, response: Response<Publication>) {
                return if (response.body() != null) callback.onSuccess(response.body())
                else callback.onFailure(Throwable("error"))
            }
        })
    }

    fun fetchFavoritePublications() {
        callback.onSuccess(database.fetchFavoritePublications())
    }

    fun updateChangeFavoriteState(data: Publication) {
        database.updateChangeFavoriteState(data)
    }
}

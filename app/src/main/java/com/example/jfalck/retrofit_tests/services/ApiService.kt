package com.example.jfalck.retrofit_tests.services

import com.example.jfalck.retrofit_tests.models.ApiModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    companion object {

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("photos")
    fun getPosts():
            Observable<List<ApiModel.Post>>
}
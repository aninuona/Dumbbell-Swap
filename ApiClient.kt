// File: app/src/main/java/com/example/dumbbellswap/api/ApiClient.kt

package com.example.dumbbellswap.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Interceptor

object ApiClient {
    private const val BASE_URL = "https://exercisedb.p.rapidapi.com/"
    private const val API_KEY = "242f93756amshfe7ff54e2ba46adp132b5fjsnbcba06d72aec"
    private const val API_HOST = "exercisedb.p.rapidapi.com"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("x-rapidapi-key", API_KEY)
                .header("x-rapidapi-host", API_HOST)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    val exerciseApiService: ExerciseApiService = retrofit.create(ExerciseApiService::class.java)
}
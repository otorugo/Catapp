package com.example.basicprojects.catapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.scalars.ScalarsConverterFactory

interface CatAppService {
    @GET("api/tags")
    fun getCatTags() : Call<String>

}

const val URL = "https://cataas.com"

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

val theCatAppService = retrofit.create(CatAppService::class.java)


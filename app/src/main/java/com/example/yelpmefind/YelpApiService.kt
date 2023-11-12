package com.example.yelpmefind

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpApiService {

    @GET("businesses/search")
    fun searchBusinesses(
        @Header("Authorization") authorization: String,
        @Query("term") term: String, // Keyword entered by the user
        @Query("sort_by") sortBy: String = "best_match",
        @Query("location") location: String = "London,Ontario",
        @Query("limit") limit: Int = 20
    ): Call<ApiResponse>

}
package com.example.data.source.remote

import com.example.domain.model.ApiQueryResponse
 import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("search/phone/page/{pageNumber}/")
    suspend fun getPhones(@Path("pageNumber") pageNumber: Int): Response<ApiQueryResponse>

}
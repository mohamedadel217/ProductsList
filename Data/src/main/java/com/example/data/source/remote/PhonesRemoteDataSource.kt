package com.example.data.source.remote

import javax.inject.Inject

class PhonesRemoteDataSource @Inject constructor(
    private val retrofitService: RetrofitService
): BaseDataSource() {

     suspend fun getPhones(pageNumber: Int) = getResult { retrofitService.getPhones(pageNumber) }
}
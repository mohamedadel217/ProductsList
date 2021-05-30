package com.example.data.repository

import androidx.lifecycle.LiveData
import com.example.data.source.local.dao.PhonesDao
import com.example.data.source.remote.PhonesRemoteDataSource
import com.example.domain.model.ResultsItem
import com.example.domain.repository.PhonesRepository
import com.example.domain.utils.Resource
import com.example.domain.utils.performGetOperation
import javax.inject.Inject

class PhonesRepositoryImp @Inject constructor(
    private val remoteDataSource: PhonesRemoteDataSource,
    private val localDataSource: PhonesDao
) : PhonesRepository {
    override suspend fun getPhones(pageNumber: Int): LiveData<Resource<List<ResultsItem>>> {
         return performGetOperation(
             databaseQuery = { localDataSource.getAllPhones() },
             networkCall = { remoteDataSource.getPhones(pageNumber) },
             saveCallResult = { localDataSource.insertAll(it.metadata.results) }
         )

    }




}
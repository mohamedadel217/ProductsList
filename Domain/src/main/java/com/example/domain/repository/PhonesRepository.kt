package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.ResultsItem
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


interface PhonesRepository {

    suspend fun getPhones(pageNumber: Int): LiveData<Resource<List<ResultsItem>>>
}
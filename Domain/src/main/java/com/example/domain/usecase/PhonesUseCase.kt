package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.model.ResultsItem
import com.example.domain.repository.PhonesRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


class PhonesUseCase @Inject constructor(private val repository: PhonesRepository) {

    suspend fun getPhones(pageNumber: Int): LiveData<Resource<List<ResultsItem>>> {
        return repository.getPhones(pageNumber)
    }

}
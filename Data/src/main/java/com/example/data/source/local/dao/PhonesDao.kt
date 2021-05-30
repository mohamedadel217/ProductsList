package com.example.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.model.ResultsItem

@Dao
interface PhonesDao {

    @Insert()
    suspend fun insertAll(phones: List<ResultsItem>)

    @Insert()
    suspend fun insert(phone: ResultsItem)

    @Query("select * from phone where id =:id")
    suspend fun getPhoneById(id: Int): ResultsItem

    @Query("select * from phone")
    fun getAllPhones(): LiveData<List<ResultsItem>>
}
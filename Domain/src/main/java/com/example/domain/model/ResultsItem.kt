package com.example.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "phone")
data class ResultsItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo
    var max_saving_percentage: Int? = null,
    @ColumnInfo
    var image: String? = null,
    @ColumnInfo
    var special_price: Long? = null,
    @ColumnInfo
    var price: Long? = null,
    @ColumnInfo
    var name: String? = null,
    @ColumnInfo
    var sku: String? = null,
    @ColumnInfo
    var brand: String? = null,
    @ColumnInfo
    var rating_average: Float? = null
): Serializable
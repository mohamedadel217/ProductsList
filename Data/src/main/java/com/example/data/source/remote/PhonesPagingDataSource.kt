package com.example.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.ResultsItem
import javax.inject.Inject

class PhonesPagingDataSource    (
    private val retrofitService: RetrofitService
) : PagingSource<Int, ResultsItem>() {


    @SuppressLint("CheckResult")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        return try {
            val nextPageNumber = params.key ?: 1

            val result =   retrofitService.getPhones(nextPageNumber)

            LoadResult.Page(
                data = result.body()?.metadata?.results!!,
                prevKey = null,
                nextKey = if(nextPageNumber<(result.body()!!.metadata.totalProducts/10)) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {

            LoadResult.Error(e)

        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
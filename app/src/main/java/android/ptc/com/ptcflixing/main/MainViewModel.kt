package android.ptc.com.ptcflixing.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.data.source.remote.PhonesPagingDataSource
import com.example.data.source.remote.RetrofitService
import com.example.domain.model.ResultsItem
import com.example.domain.usecase.PhonesUseCase
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val phonesUseCase: PhonesUseCase,
    private val retrofitService: RetrofitService
) : ViewModel() {

    suspend fun getPhones(pageNumber: Int): LiveData<Resource<List<ResultsItem>>> {
        return phonesUseCase.getPhones(pageNumber)

    }

    // this variable is for paging
    val phones = Pager(
            PagingConfig(pageSize = 10)
        ) {
            PhonesPagingDataSource(retrofitService)
        }.flow.cachedIn(viewModelScope)
}

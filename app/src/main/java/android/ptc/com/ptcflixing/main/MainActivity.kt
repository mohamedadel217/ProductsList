package android.ptc.com.ptcflixing.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.ptc.com.ptcflixing.databinding.ActivityMainBinding
import android.ptc.com.ptcflixing.main.adapters.PhonesAdapter
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.domain.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PhonesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = PhonesAdapter()
        binding.phoneRecyclerView.adapter = adapter
        setupObservers()
    }

    private fun setupObservers() {
        MainScope().launch {
            viewModel.getPhones(1).observe(this@MainActivity, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        if (!it.data.isNullOrEmpty()) adapter.getData(ArrayList(it.data))
                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()

                    Resource.Status.LOADING ->
                        binding.progressBar.visibility = View.VISIBLE
                }
            })


            /** For making default loadstate **/
//                products?.loadStateFlow?.collectLatest { loadStates ->
//                    progressBar?.visible(loadStates.refresh is LoadState.Loading)
//                }
//
            /** For getting paging data and send it to adapter **/
//            viewModel?.phones.collectLatest { pagedData ->
//
//                productsPagingAdapter?.submitData(pagedData)
//
//            }
        }


    }
}

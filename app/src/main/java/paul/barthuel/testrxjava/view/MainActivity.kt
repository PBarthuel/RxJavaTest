package paul.barthuel.testrxjava.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.functions.Consumer
import paul.barthuel.testrxjava.R
import paul.barthuel.testrxjava.retrofit.CountriesService
import paul.barthuel.testrxjava.retrofit.CountryModel
import paul.barthuel.testrxjava.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: ListViewModel
    private val adapter = CountryListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ListViewModel(CountriesService())

        mViewModel.getCountries()
        loadView()
        listenToObservables()
    }

    private fun listenToObservables() {
        mViewModel.resultListObservable.subscribe {
            updateCountriesList(it)
        }
    }

    private fun loadView() {
        val recyclerView: RecyclerView = findViewById(R.id.main_rv)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun updateCountriesList(countriesModel: List<CountryModel>?) {
        adapter.submitList(countriesModel)
    }

    override fun onStop() {
        super.onStop()
        mViewModel.cancelNetworkConnections()
    }
}
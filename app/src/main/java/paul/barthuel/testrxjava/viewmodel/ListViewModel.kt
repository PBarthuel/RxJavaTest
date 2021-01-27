package paul.barthuel.testrxjava.viewmodel

import CountryDetail
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import paul.barthuel.testrxjava.retrofit.CountriesService
import paul.barthuel.testrxjava.retrofit.CountryModel

class ListViewModel() {

    lateinit var resultListObservable: PublishSubject<List<CountryModel>>

    private val compositeDisposable = CompositeDisposable()
    private val schedulersWrapper = SchedulersWrapper()

    private lateinit var countriesService: CountriesService

    constructor(mCountriesService: CountriesService): this() {
        countriesService = mCountriesService
        resultListObservable = PublishSubject.create()
    }

    fun getCountries() {
        val disposable: Disposable = countriesService
                .fetchCountries()!!
                .subscribeOn(schedulersWrapper.io())
                .observeOn(schedulersWrapper.main())
                .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>() {
                    override fun onSuccess(t: List<CountryModel>?) {
                        resultListObservable.onNext(t)
                    }
                    override fun onError(e: Throwable?) {
                        resultListObservable.isEmpty
                    }
                })
        compositeDisposable.add(disposable)
    }

    fun cancelNetworkConnections() {
        compositeDisposable.clear()
    }

    private fun fetchInfoFrom(it: List<CountryDetail>): ArrayList<CountryModel> {
        val countries = arrayListOf<CountryModel>()
        for (countryDetail in it) {
            val countryModel = CountryModel(countryDetail.name,
            countryDetail.capital,
            countryDetail.flagPNG)
            countries.add(countryModel)
        }
        return countries
    }
}
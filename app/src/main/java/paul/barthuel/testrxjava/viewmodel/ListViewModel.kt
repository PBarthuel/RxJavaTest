package paul.barthuel.testrxjava.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import paul.barthuel.testrxjava.model.CountriesService
import paul.barthuel.testrxjava.model.CountryModel

class ListViewModel : ViewModel() {
    @JvmField
    var countries = MutableLiveData<List<CountryModel?>?>()
    private val countriesService = CountriesService.getInstance()
    private val disposable = CompositeDisposable()
    val countriesLiveData: LiveData<List<CountryModel?>?>
        get() = countries

    fun fetch() {
        disposable.add(
                countriesService
                        .countries
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<CountryModel?>?>() {
                            override fun onSuccess(@NonNull t: @NonNull List<CountryModel?>?) {
                                countries.value = t
                            }

                            override fun onError(e: @NonNull Throwable?) {}
                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
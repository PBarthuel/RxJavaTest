package paul.barthuel.testrxjava.retrofit

import CountryDetail
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {

    private var mRetrofit: Retrofit? = null
    private val BASE_URL = "https://raw.githubusercontent.com/"

    fun fetchCountries(): Single<List<CountryModel>>? {
        return getRetrofit()?.create(CountriesApi::class.java)?.fetchCountries()
    }

    private fun getRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            mRetrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(client)
                    .build()
        }
        return mRetrofit
    }

}
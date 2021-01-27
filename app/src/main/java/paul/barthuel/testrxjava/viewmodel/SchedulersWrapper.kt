package paul.barthuel.testrxjava.viewmodel

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulersWrapper {
    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}

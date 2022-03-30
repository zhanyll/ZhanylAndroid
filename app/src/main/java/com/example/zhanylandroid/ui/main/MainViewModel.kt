package com.example.zhanylandroid.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.zhanylandroid.App
import com.example.zhanylandroid.ui.Event
import com.example.zhanylandroid.R
import com.example.zhanylandroid.data.repo.BreakingBadRepo
import com.example.zhanylandroid.data.models.Episodes
import com.example.zhanylandroid.domain.useCases.GetEpisodeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val breakingBadRepo = BreakingBadRepo(
        getApplication<App>().breakingBadApi,
        getApplication<App>().database.episodesDao()
    )

    private val getEpisodeUseCase = GetEpisodeUseCase(breakingBadRepo)

    val episodesLiveData: LiveData<List<Episodes>> =
        getApplication<App>().database.episodesDao().getAll()


    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?> get() = _event

    init {
        loadEpisodes()
    }

    fun loadEpisodes(){
        _event.value = Event.ShowLoadingToast
        compositeDisposable.add(
            getEpisodeUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate{ _event.value = Event.ShowFinishedLoadingToast }
                .doOnError{ handleError(it) }
                .subscribe()
        )
    }

    private fun handleError(it: Throwable) {
        _event.value = when (it) {
            is UnknownHostException -> Event.ShowToast(R.string.no_internet)
            else -> Event.ShowToast(R.string.unknown_error)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun clearEvents() {
        _event.value = null
    }
}
package com.example.zhanylandroid.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.zhanylandroid.ui.Event
import com.example.zhanylandroid.R
import com.example.zhanylandroid.data.models.Episodes
import com.example.zhanylandroid.domain.useCases.GetEpisodeUseCase
import com.example.zhanylandroid.domain.useCases.GetEpisodesAsLiveDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val getEpisodeUseCase: GetEpisodeUseCase,
    getEpisodesAsLiveDataUseCase: GetEpisodesAsLiveDataUseCase
): AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val episodesLiveData: LiveData<List<Episodes>> =
        getEpisodesAsLiveDataUseCase()

    private val _event = MutableLiveData<Event?>()

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
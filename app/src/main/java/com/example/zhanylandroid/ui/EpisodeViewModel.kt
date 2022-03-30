package com.example.zhanylandroid.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zhanylandroid.App
import com.example.zhanylandroid.data.repo.BreakingBadRepo
import com.example.zhanylandroid.domain.useCases.GetEpisodeByIdUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EpisodeViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private  var id: Long? = null
    fun setId(id: Long?) {
        this.id = id
    }

    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?> get() = _event

    private val breakingBadRepo = BreakingBadRepo(
        getApplication<App>().breakingBadApi,
        getApplication<App>().database.episodesDao()
    )

    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase =
        GetEpisodeByIdUseCase(breakingBadRepo)

    fun fetchEpisode() {
        id?.let {
            compositeDisposable.add(
                getEpisodeByIdUseCase(it)
                    .doOnSuccess {
                        _event.value = Event.FetchedEpisode(it)
                    }
                    .map { episode ->
                        episode
                    }
                    .subscribe()
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
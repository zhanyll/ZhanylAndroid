package com.example.zhanylandroid.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zhanylandroid.domain.useCases.GetEpisodeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    application: Application,
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase
): AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private  var id: Long? = null
    fun setId(id: Long?) {
        this.id = id
    }

    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?> get() = _event

    fun fetchEpisode() {
        id?.let {
            compositeDisposable.add(
                getEpisodeByIdUseCase(it)
                    .doOnSuccess { episode ->
                        _event.value = Event.FetchedEpisode(episode)
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
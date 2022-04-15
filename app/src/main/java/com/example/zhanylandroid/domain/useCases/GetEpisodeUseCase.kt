package com.example.zhanylandroid.domain.useCases

import com.example.zhanylandroid.data.repo.BreakingBadRepo
import com.example.zhanylandroid.data.models.Episodes
import com.example.zhanylandroid.extensions.toEpisodesEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(private val breakingBadRepo: BreakingBadRepo) {
    operator fun invoke(): Observable<Unit> {
        return breakingBadRepo.getEpisodes()
            .map {
                Thread.sleep(5000)
                it
            }
            .map {
                val listEp = mutableListOf<Episodes>()
                it.forEach { each ->
                    listEp.add(each.toEpisodesEntity())
                }
                listEp.toList()
            }
            .map{
                breakingBadRepo.insertList(it)
            }
            .observeOn(AndroidSchedulers.mainThread())

    }
}
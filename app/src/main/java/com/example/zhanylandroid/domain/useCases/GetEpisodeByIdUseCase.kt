package com.example.zhanylandroid.domain.useCases

import com.example.zhanylandroid.data.models.Episode
import com.example.zhanylandroid.data.repo.BreakingBadRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class GetEpisodeByIdUseCase(
    private val breakingBadRepo: BreakingBadRepo
) {
    operator fun invoke(id: Long): Single<Episode> {
        return breakingBadRepo.getEpisodeById(id)
            .map {
                it[0]
            }
            .observeOn(AndroidSchedulers.mainThread())
        // почему мы здесь не делаем .subscribe ?
    }
}
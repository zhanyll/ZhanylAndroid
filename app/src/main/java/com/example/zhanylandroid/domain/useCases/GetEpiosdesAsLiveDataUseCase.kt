package com.example.zhanylandroid.domain.useCases

import com.example.zhanylandroid.data.storage.EpisodesDao
import javax.inject.Inject

class GetEpisodesAsLiveDataUseCase @Inject constructor(private val episodesDao: EpisodesDao) {
    operator fun invoke() = episodesDao.getAll()
}
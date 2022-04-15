package com.example.zhanylandroid.data.repo
import com.example.zhanylandroid.data.models.Episode
import com.example.zhanylandroid.data.network.BreakingBadApi
import com.example.zhanylandroid.data.models.Episodes
import com.example.zhanylandroid.data.storage.EpisodesDao
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BreakingBadRepo @Inject constructor(
    private val breakingBadApi: BreakingBadApi,
    private val episodesDao: EpisodesDao
) {
    fun getEpisodes(): Observable<List<Episode>> {
        return breakingBadApi.getEpisodes()
            .subscribeOn(Schedulers.io())
    }

    fun getEpisodeById(episodeId: Long): Single<List<Episode>> {
        return breakingBadApi.getEpisodeById(episodeId)
            .subscribeOn(Schedulers.io())
    }

    fun insertList(episodeList: List<Episodes>) {
        episodesDao.insertList(episodeList)
    }

    fun insert(episode: Episodes) {
        episodesDao.insert(episode)
    }

    fun delete(episode: Episodes) {
        episodesDao.delete(episode)
    }

    fun update(episode: Episodes) {
        episodesDao.update(episode)
    }
}
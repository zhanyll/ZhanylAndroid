package com.example.zhanylandroid.data.network

import com.example.zhanylandroid.data.models.Episode
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BreakingBadApi {
    @GET("episodes")
    fun getEpisodes(): Observable<List<Episode>>

    @GET("episodes/{id}")
    fun getEpisodeById(@Path("id") id: Long): Single<List<Episode>>
}
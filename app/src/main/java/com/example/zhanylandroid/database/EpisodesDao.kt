package com.example.zhanylandroid.database

import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface EpisodesDao {
    @Query("SELECT * FROM Episodes")
    fun getAll(): Observable<List<Episodes>>

    @Query("SELECT * FROM Episodes WHERE episode_id = :episode_id")
    fun getById(episode_id: Long?): Single<Episodes>

    @Insert
    fun insert(episode: Episodes): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(characters: List<Episodes>)

    @Update
    fun update(episode: Episodes)

    @Delete
    fun delete(episode: Episodes)
}
package com.example.zhanylandroid.data.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.zhanylandroid.data.models.Episodes
import io.reactivex.Single

@Dao
interface EpisodesDao {
    @Query("SELECT * FROM Episodes")
    fun getAll(): LiveData<List<Episodes>>

    @Query("SELECT * FROM Episodes WHERE episode_id = :episode_id")
    fun getById(episode_id: Long?): Single<Episodes>

    @Insert
    fun insert(episode: Episodes): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(episodes: List<Episodes>)

    @Update
    fun update(episode: Episodes)

    @Delete
    fun delete(episode: Episodes)
}
package com.example.zhanylandroid.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zhanylandroid.data.models.Episodes

@Database(entities = [Episodes::class], version = 1)
abstract class AppDatabase: RoomDatabase()  {
    abstract fun episodesDao(): EpisodesDao
}
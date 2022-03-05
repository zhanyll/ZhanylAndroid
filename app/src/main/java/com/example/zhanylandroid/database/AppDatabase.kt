package com.example.zhanylandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Episodes::class], version = 1)
abstract class AppDatabase: RoomDatabase()  {
    abstract fun episodesDao(): EpisodesDao
}
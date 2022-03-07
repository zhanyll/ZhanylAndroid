package com.example.zhanylandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zhanylandroid.TypeListConverter

@TypeConverters(TypeListConverter::class)
@Database(entities = [Episodes::class], version = 1)
abstract class AppDatabase: RoomDatabase()  {
    abstract fun episodesDao(): EpisodesDao
}
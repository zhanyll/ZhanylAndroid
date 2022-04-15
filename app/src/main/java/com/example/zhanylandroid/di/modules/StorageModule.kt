package com.example.zhanylandroid.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.zhanylandroid.data.storage.AppDatabase
import com.example.zhanylandroid.data.storage.EpisodesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Singleton
    fun database(context: Application): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideEpisodeDao(database: AppDatabase): EpisodesDao {
        return database.episodesDao()
    }
}
package com.example.zhanylandroid.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Episodes(
    @PrimaryKey(autoGenerate = true)
    val episode_id: Long?,
    val title: String,
    val season: String,
    val air_date: String,
    val characters: List<String>,
    val episode: String,
    val series: String
)

package com.example.zhanylandroid.extensions

import com.example.zhanylandroid.data.models.Episode
import com.example.zhanylandroid.data.models.Episodes

fun Episode.toEpisodesEntity(): Episodes {
    return Episodes(
        episode_id = this.episode_id,
        title = this.title,
        season = this.season,
        air_date = this.air_date,
        characters = this.characters.toString(),
        episode = this.episode,
        series = this.series
    )
}
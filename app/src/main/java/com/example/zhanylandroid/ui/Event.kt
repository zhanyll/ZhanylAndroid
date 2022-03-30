package com.example.zhanylandroid.ui

import androidx.annotation.StringRes
import com.example.zhanylandroid.data.models.Episode

sealed class Event() {
    class ShowToast(@StringRes val resId: Int): Event()
    object ShowLoadingToast: Event()
    object ShowFinishedLoadingToast: Event()
    class FetchedEpisode(val episode: Episode): Event()
}
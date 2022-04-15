package com.example.zhanylandroid.ui

import androidx.fragment.app.Fragment

interface Clicked {
    fun onClick(id: Long) {}

    fun onMain(fragment: Fragment, addToBackStack: Boolean? = true) {}
}
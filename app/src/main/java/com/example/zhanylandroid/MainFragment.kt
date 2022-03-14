package com.example.zhanylandroid

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class MainFragment: Fragment(R.layout.main_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val img = view.findViewById<AppCompatImageView>(R.id.img)

        object : CountDownTimer(1000, 10000) {
            override fun onTick(p0: Long) {
                Glide.with(view.context)
                    .load("https://c.tadst.com/gfx/600x337/winter-lake.jpg?1")
                    .into(img)
            }

            override fun onFinish() {

            }
        }
    }
}
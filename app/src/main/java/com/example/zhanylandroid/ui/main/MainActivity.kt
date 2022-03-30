package com.example.zhanylandroid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zhanylandroid.ui.Clicked
import com.example.zhanylandroid.R
import com.example.zhanylandroid.databinding.ActivityMainBinding
import com.example.zhanylandroid.ui.EpisodeFragment

class MainActivity : AppCompatActivity(), Clicked {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MainFragment())
            .commit()
    }

    override fun onMain() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onClick(id: Long) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, EpisodeFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }
}
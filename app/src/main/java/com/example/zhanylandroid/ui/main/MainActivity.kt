package com.example.zhanylandroid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.zhanylandroid.ui.Clicked
import com.example.zhanylandroid.R
import com.example.zhanylandroid.databinding.ActivityMainBinding
import com.example.zhanylandroid.ui.EpisodeFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Clicked {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            onMain(MainFragment(),false)
        }
    }

    override fun onClick(id: Long) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, EpisodeFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    } // using it

    override fun onMain(fragment: Fragment, addToBackStack: Boolean?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment).apply {
                if(addToBackStack == true){
                    addToBackStack("")
                }
            }
            .commit()
    }
}
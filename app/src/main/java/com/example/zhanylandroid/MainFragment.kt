package com.example.zhanylandroid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.zhanylandroid.databinding.MainFragmentBinding

class MainFragment: Fragment(R.layout.main_fragment) {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)

        binding.apply {
            btn.setOnClickListener {
                val text = edit.text.toString().trim()
                val result = count(text)
                binding.txt.text = "count result: $result"
            }
        }
    }

    private fun count(text: String): Int {
        val regex = Regex("[^A-Za-z0-9]")
        val result = regex.replace(text, " ")
        val lst = result.split(" ", "\n")
        var res = 0
        for (i in lst) {
            if (i.length % 2 == 0) {
                res += 1
            }
        }
        return res
    }
}
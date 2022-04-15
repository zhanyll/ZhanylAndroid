package com.example.zhanylandroid.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zhanylandroid.*
import com.example.zhanylandroid.databinding.MainFragmentBinding
import com.example.zhanylandroid.ui.Clicked
import com.example.zhanylandroid.ui.main.rv.MyAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.main_fragment) {
    private lateinit var vm: MainViewModel
    private lateinit var listener: Clicked
    private lateinit var adapter: MyAdapter

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Clicked
        } catch (e: Exception){ print("Activity must implement FragmentListener")}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)
        adapter = MyAdapter{ listener.onClick(it.episode_id!!) }

        setAdapter()
        setRefresh()
    }

    private fun setRefresh() {
        binding.run {
            swipeRefreshLayout.setOnRefreshListener { vm.loadEpisodes() }
            swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright
            )
        }
    }

    private fun setAdapter() {
        binding.run {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(activity)
            recycler.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        }
        vm.episodesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vm.clearEvents()
        _binding = null
    }
}
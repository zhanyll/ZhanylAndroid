package com.example.zhanylandroid

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.zhanylandroid.database.Episodes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainFragment: Fragment(R.layout.main_fragment) {
    private val api get() = Injector.breakingBadApi
    private val dbInstance get() = Injector.database
    private lateinit var listener: Clicked
    private lateinit var adapter: MyAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Clicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MyAdapter{ listener.onClick(it.episode_id!!) }
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        getAll()

        swipeRefresh = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefresh.setOnRefreshListener { getAll() }
        swipeRefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright
        )
    }

    private fun getAll() {
        api.getEpisodes()
            .subscribeOn(Schedulers.io())
            .map {
                val listEp = mutableListOf<Episodes>()
                it.forEach {
                    val episode = Episodes(
                        episode_id = it.episode_id,
                        title = it.title,
                        season = it.season,
                        air_date = it.air_date,
                        characters = it.characters,
                        episode = it.episode,
                        series = it.series
                    )
                    listEp.add(episode)
                }
                listEp.toList()
                dbInstance.episodesDao().insertList(listEp)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { list ->
                adapter.setData(list)
            }
            .subscribe()
    }
}
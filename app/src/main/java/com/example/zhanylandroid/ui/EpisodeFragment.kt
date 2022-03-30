package com.example.zhanylandroid.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zhanylandroid.Injector
import com.example.zhanylandroid.R
import com.example.zhanylandroid.databinding.EpisodeFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EpisodeFragment: Fragment(R.layout.episode_fragment) {
    private val api get() = Injector.breakingBadApi
    private var _binding: EpisodeFragmentBinding?= null
    private val binding get() = _binding!!
    private lateinit var listener: Clicked
    private lateinit var viewModel: EpisodeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Clicked
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EpisodeViewModel::class.java]
        viewModel.setId(arguments?.getLong(Long::class.java.canonicalName))
        viewModel.fetchEpisode()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EpisodeFragmentBinding.bind(view)

        subscribeToLiveData()
    }

    fun subscribeToLiveData() {
        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is Event.FetchedEpisode -> {
                    binding.episodeTitle.text = "Title: ${it.episode.title}"
                    binding.episodeSeason.text = "Season: ${it.episode.season}"
                    binding.episode.text = "Episode: ${it.episode.episode}"
                    binding.episodeCharacters.text = "Characters: ${it.episode.characters}"
                    binding.episodeDate.text = "Date: ${it.episode.air_date}"
                    binding.episodesSeries.text = "Series: ${it.episode.series}"
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(id: Long): EpisodeFragment {
            val args = Bundle().apply { putLong(Long::class.java.canonicalName, id) }
            return EpisodeFragment().apply { arguments = args }
        }
    }
}
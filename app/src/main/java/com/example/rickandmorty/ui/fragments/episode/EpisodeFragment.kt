package com.example.rickandmorty.ui.fragments.episode

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.ui.adapters.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment :
    BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(R.layout.fragment_episode) {
    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val adapter = EpisodeAdapter(arrayListOf())
    private var count = 1

    override fun initialize() {
        setupRecyclerView()
    }


    override fun setupSubscribe() {
        subscribeToEpisode()
    }

    private fun setupRecyclerView() {
        binding.rvEpisode.adapter = adapter
        binding.rvEpisode.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(requireActivity(), "Last", Toast.LENGTH_LONG).show()
                    ++count
                    subscribeToEpisode()
                }
            }
        })
    }

    private fun subscribeToEpisode() {
        lifecycleScope.launch {
            viewModel.fetchEpisode(page = count).collect {
                when (it) {
                    is Resource.Error -> {
                        Log.e("rick", it.message.toString())
                    }
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        it.data?.let { it1 -> adapter.addNewItems(it1.results) }
                        Log.e("rick", it.data?.results.toString())
                    }
                }
            }
        }
    }
}
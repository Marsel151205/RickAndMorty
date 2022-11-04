package com.example.rickandmorty.ui.fragments.location

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.common.Resource
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment :
    BaseFragment<FragmentLocationBinding, LocationViewModel>(R.layout.fragment_location) {
    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val adapter = LocationAdapter(arrayListOf())
    private var count = 1

    override fun initialize() {
        setupRecyclerView()
    }


    override fun setupSubscribe() {
        subscribeToLocation()
    }

    private fun setupRecyclerView() {
        binding.rvLocation.adapter = adapter
        binding.rvLocation.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(requireActivity(), "Last", Toast.LENGTH_LONG).show()
                    ++count
                    subscribeToLocation()
                }
            }
        })
    }

    private fun subscribeToLocation() {
        lifecycleScope.launch {
            viewModel.fetchLocation(page = count).collect {
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
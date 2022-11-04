package com.example.rickandmorty.ui.fragments.episode

import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val repository: EpisodeRepository) :
    BaseViewModel() {

        fun fetchEpisode(page: Int) = repository.fetchEpisode(page)
}
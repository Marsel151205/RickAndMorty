package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.base.BaseRepository
import com.example.rickandmorty.data.remote.apiservices.EpisodeApiService
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val service: EpisodeApiService) :
    BaseRepository() {

    fun fetchEpisode(page: Int) = doRequest {
        service.fetchEpisode(page)
    }
}
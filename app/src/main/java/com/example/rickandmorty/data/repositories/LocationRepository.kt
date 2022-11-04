package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.base.BaseRepository
import com.example.rickandmorty.data.remote.apiservices.LocationApiService
import javax.inject.Inject

class LocationRepository @Inject constructor(private val service: LocationApiService) :
    BaseRepository() {

    fun fetchLocation(page: Int) = doRequest {
        service.fetchLocation(page)
    }
}
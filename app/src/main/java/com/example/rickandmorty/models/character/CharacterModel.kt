package com.example.rickandmorty.models.character

import com.example.rickandmorty.base.IBaseDiffModel
import com.example.rickandmorty.models.Origin
import com.example.rickandmorty.models.episode.EpisodeModel
import com.example.rickandmorty.models.location.LocationModel
import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("location")
    val location: LocationModel
) : IBaseDiffModel<Int>
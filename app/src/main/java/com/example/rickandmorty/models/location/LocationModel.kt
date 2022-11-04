package com.example.rickandmorty.models.location

import com.example.rickandmorty.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class LocationModel(
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : IBaseDiffModel<Int>
package com.example.rickandmorty.models.location

import com.example.rickandmorty.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String
) : IBaseDiffModel<Int>
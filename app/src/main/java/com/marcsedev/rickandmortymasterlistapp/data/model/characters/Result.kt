package com.marcsedev.rickandmortymasterlistapp.data.model.characters

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String?,
    val location: LocationData,
    val name: String,
    val origin: OriginData,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
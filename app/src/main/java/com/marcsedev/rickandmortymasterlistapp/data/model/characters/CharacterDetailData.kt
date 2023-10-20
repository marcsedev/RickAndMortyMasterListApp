package com.marcsedev.rickandmortymasterlistapp.data.model.characters

data class CharacterDetailData(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originData: OriginData?,
    val locationData: LocationData?,
    val image: String?,
    val episode: List<String>?,
    val url: String,
    val created: String
)

package com.marcsedev.rickandmortymasterlistapp.data.network.model.response

import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.InfoData

data class RickAndMortyResponseData(
    val infoData: InfoData,
    val results: List<CharacterData>
)
package com.marcsedev.rickandmortymasterlistapp.data.model.response

import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterDetailData
import com.marcsedev.rickandmortymasterlistapp.data.model.characters.InfoData

data class CharacterResponse(
    val info: InfoData,
    val results: List<CharacterDetailData>
)
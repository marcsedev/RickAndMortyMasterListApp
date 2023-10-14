package com.marcsedev.rickandmortymasterlistapp.data.model.response

import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.model.characters.InfoData

data class CharacterResponse(
    val info: InfoData,
    val results: List<CharacterData>
)
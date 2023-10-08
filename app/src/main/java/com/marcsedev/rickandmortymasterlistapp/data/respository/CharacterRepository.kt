package com.marcsedev.rickandmortymasterlistapp.data.respository

import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.network.model.response.CharacterResponse

class CharacterRepository {

    private val characterService = RetrofitInstance.characterService

    suspend fun getCharactersList(): CharacterResponse {
        return characterService.getCharactersList()
    }

    suspend fun getCharacter(id: Int): CharacterData {
        return characterService.getCharacter(id = id)
    }
}
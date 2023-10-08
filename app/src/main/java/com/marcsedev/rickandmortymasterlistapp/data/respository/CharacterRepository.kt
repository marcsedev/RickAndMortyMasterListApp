package com.marcsedev.rickandmortymasterlistapp.data.respository

import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData

class CharacterRepository {

    private val characterService = RetrofitInstance.characterService

    suspend fun getCharactersList(): List<CharacterData> {
        return characterService.getCharactersList()
    }

    suspend fun getCharacter(id: Int): CharacterData {
        return characterService.getCharacter(id = id)
    }
}
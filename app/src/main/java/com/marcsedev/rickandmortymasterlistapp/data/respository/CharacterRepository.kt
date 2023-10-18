package com.marcsedev.rickandmortymasterlistapp.data.respository

import com.marcsedev.rickandmortymasterlistapp.data.model.response.CharacterResponse
import com.marcsedev.rickandmortymasterlistapp.data.network.CharacterService

class CharacterRepository {
    private val api = CharacterService()

    suspend fun getCharactersList(page: Int): CharacterResponse {
        return api.getCharactersList(page)
    }
}
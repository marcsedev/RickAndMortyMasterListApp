package com.marcsedev.rickandmortymasterlistapp.data.respository

import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterDetailData
import com.marcsedev.rickandmortymasterlistapp.data.model.response.CharacterResponse
import com.marcsedev.rickandmortymasterlistapp.data.network.CharacterService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api : CharacterService
) {

    suspend fun getCharactersList(page: Int): CharacterResponse {
        return api.getCharactersList(page)
    }

    suspend fun getCharacterById(id: Int): CharacterDetailData {
        return api.getCharacterById(id)
    }
}
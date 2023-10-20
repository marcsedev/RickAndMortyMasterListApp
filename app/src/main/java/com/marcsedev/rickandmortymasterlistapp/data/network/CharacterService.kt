package com.marcsedev.rickandmortymasterlistapp.data.network

import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterDetailData
import com.marcsedev.rickandmortymasterlistapp.data.model.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(
private val characterClient: CharacterClient
) {

    suspend fun getCharactersList(page: Int): CharacterResponse {
        return withContext(Dispatchers.IO) {
            characterClient.getCharactersList(page)
        }
    }

    suspend fun getCharacterById(id: Int): CharacterDetailData {
        return withContext(Dispatchers.IO) {
            characterClient.getCharacterById(id)
        }
    }
}
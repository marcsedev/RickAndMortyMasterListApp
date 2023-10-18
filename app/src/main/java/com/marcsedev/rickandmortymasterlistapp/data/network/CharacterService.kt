package com.marcsedev.rickandmortymasterlistapp.data.network

import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.model.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterService {

    //private val characterService = RetrofitInstance.characterClient
    private val characterService = RetrofitHelper.getRetrofit()

    suspend fun getCharactersList(page: Int): CharacterResponse {
        return withContext(Dispatchers.IO) {
            //characterService.getCharactersList(page)
            characterService.create(CharacterClient::class.java).getCharactersList(page)
        }
    }

    suspend fun getCharacter(id: Int): CharacterData {
        return withContext(Dispatchers.IO) {
            characterService.create(CharacterClient::class.java).getCharacter(id)
        }
    }
}
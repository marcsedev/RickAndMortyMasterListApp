package com.marcsedev.rickandmortymasterlistapp.domain

import com.marcsedev.rickandmortymasterlistapp.data.model.response.CharacterResponse
import com.marcsedev.rickandmortymasterlistapp.data.respository.CharacterRepository

class CharactersListUseCase {
    private val repository = CharacterRepository()

    suspend operator fun invoke(page: Int): CharacterResponse {
        return repository.getCharactersList(page)
    }

}
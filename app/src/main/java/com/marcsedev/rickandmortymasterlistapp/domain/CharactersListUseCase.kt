package com.marcsedev.rickandmortymasterlistapp.domain

import com.marcsedev.rickandmortymasterlistapp.data.model.response.CharacterResponse
import com.marcsedev.rickandmortymasterlistapp.data.respository.CharacterRepository
import javax.inject.Inject

class CharactersListUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(page: Int): CharacterResponse {
        return repository.getCharactersList(page)
    }

}
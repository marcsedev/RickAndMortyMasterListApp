package com.marcsedev.rickandmortymasterlistapp.data.respository

import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.network.model.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    // Get all characters
    @GET("character")
    suspend fun getCharactersList(
        @Query("page") page: Int
    ): CharacterResponse

    // Get a single character
    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterData
}
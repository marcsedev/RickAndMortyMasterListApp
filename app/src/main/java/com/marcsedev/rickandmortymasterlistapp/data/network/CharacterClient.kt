package com.marcsedev.rickandmortymasterlistapp.data.network

import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.model.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterClient {
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
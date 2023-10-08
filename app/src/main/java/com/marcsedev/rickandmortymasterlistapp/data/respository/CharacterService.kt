package com.marcsedev.rickandmortymasterlistapp.data.respository

import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    //Get all characters
    @GET("character")
    suspend fun getCharactersList(): List<CharacterData>

    //Get a single character
    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): CharacterData

}
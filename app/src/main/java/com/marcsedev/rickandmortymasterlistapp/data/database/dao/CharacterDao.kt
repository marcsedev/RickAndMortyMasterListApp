package com.marcsedev.rickandmortymasterlistapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marcsedev.rickandmortymasterlistapp.data.database.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters_table /*ORDER BY id ASC*/")
    suspend fun getAllCharacters():List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters:List<CharacterEntity>)
}
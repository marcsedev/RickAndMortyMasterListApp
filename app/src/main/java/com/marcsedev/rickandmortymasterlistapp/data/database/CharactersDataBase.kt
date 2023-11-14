package com.marcsedev.rickandmortymasterlistapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marcsedev.rickandmortymasterlistapp.data.database.dao.CharacterDao
import com.marcsedev.rickandmortymasterlistapp.data.database.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharactersDataBase: RoomDatabase() {

    abstract fun getCharacterDao():CharacterDao
}
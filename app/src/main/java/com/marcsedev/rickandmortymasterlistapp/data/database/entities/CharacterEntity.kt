package com.marcsedev.rickandmortymasterlistapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0, // Puedes cambiar el tipo de ID según tus necesidades
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String // Aquí puedes almacenar la URL de la imagen del personaje
)
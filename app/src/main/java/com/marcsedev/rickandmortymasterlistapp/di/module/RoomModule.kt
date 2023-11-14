package com.marcsedev.rickandmortymasterlistapp.di.module

import android.content.Context
import androidx.room.Room
import com.marcsedev.rickandmortymasterlistapp.data.database.CharactersDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val CHARACTERS_DATABASE_NAME = "characters_database"

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CharactersDataBase::class.java, CHARACTERS_DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun providesCharactersDao(db: CharactersDataBase) = db.getCharacterDao()
}
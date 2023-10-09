package com.marcsedev.rickandmortymasterlistapp.ui.list.masterList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.respository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MasterListViewModel : ViewModel() {

    private val repository = CharacterRepository()

    private val _charactersList = MutableLiveData<List<CharacterData>>()
    val charactersList: LiveData<List<CharacterData>> = _charactersList

    private val _character = MutableLiveData<CharacterData>()
    val character: LiveData<CharacterData> = _character

    init {
        getCharacter()
        getCharactersList()
    }

    fun setCharactersList(characterList: List<CharacterData>) {
        _charactersList.value = characterList
    }

    private fun getCharactersList() {
        viewModelScope.launch {
            try {
                val response = repository.getCharactersList()
                _charactersList.value = response.results
                response.results.forEach {
                    Log.e("characters", it.toString())
                }
            } catch (e: Exception) {
                Log.e("characters", "Error fetching characters: ${e.message}")
                // Handle the error according to your needs
            }
        }
    }

    private fun getCharacter() {
        viewModelScope.launch {
            try {
                val character = repository.getCharacter(2)
                _character.value = character
                Log.e("character", "$character")
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
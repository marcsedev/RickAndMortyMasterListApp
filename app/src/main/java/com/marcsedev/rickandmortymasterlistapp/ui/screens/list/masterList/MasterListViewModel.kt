package com.marcsedev.rickandmortymasterlistapp.ui.screens.list.masterList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.respository.CharacterRepository
import kotlinx.coroutines.launch

class MasterListViewModel : ViewModel() {

    private val repository = CharacterRepository()

    private val _charactersList = MutableLiveData<List<CharacterData>>()
    val charactersList: LiveData<List<CharacterData>> = _charactersList

    init {
        getCharactersList()
    }

    fun setCharactersList(characterList: List<CharacterData>) {
        _charactersList.value = characterList
    }

    private var currentPage = 1

    fun loadMoreCharacters() {
       viewModelScope.launch {
           currentPage++
           getCharactersList()
       }
    }

    private fun getCharactersList() {
        viewModelScope.launch {
            try {
                val response = repository.getCharactersList(currentPage)
                val newList = _charactersList.value.orEmpty() + response.results
                _charactersList.value = newList
                response.results.forEach {
                    Log.e("characters", it.toString())
                }
            } catch (e: Exception) {
                Log.e("characters", "Error al obtener personajes: ${e.message}")
            }
        }
    }
}
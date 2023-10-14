package com.marcsedev.rickandmortymasterlistapp.ui.screens.masterList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.domain.CharactersListUseCase
import kotlinx.coroutines.launch

class MasterListViewModel : ViewModel() {

    val charactersListUseCase = CharactersListUseCase()

    //private val repository = CharacterService()

    private val _charactersList = MutableLiveData<List<CharacterData>>()
    val charactersList: LiveData<List<CharacterData>> = _charactersList

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private var currentPage = 1

    init {
        getCharactersList()
    }

    fun setCharactersList(characterList: List<CharacterData>) {
        _isLoading.value = false
        _charactersList.value = characterList
    }

    fun loadMoreCharacters() {
        viewModelScope.launch {
            currentPage++
            getCharactersList()
        }
    }

    private fun getCharactersList() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                //val response = repository.getCharactersList(currentPage)
                val response = charactersListUseCase(currentPage)
                val newList = _charactersList.value.orEmpty() + response.results
                _charactersList.value = newList
            } catch (e: Exception) {
                Log.e("characters", "Error al obtener personajes: ${e.message}")
            }
            _isLoading.value = false
        }
    }
}
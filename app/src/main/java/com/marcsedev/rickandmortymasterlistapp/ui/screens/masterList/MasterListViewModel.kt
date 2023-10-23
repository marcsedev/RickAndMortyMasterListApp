package com.marcsedev.rickandmortymasterlistapp.ui.screens.masterList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterDetailData
import com.marcsedev.rickandmortymasterlistapp.domain.CharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MasterListViewModel @Inject constructor(
    private val charactersListUseCase: CharactersListUseCase
) : ViewModel() {

    private val _charactersList = MutableStateFlow<List<CharacterDetailData>>(emptyList())
    val charactersList = _charactersList.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    var isLoading = _isLoading.asStateFlow()

    private var currentPage = 1

    //private var isFetching = false

    init {
        getCharactersList()
    }

    fun setCharactersList(characterList: List<CharacterDetailData>) {
        _isLoading.value = false
        _charactersList.value = characterList
    }

    fun loadMoreCharacters() {
        // if (!isFetching) {
        currentPage++
        getCharactersList()
        //}
    }

    private fun getCharactersList() {
        viewModelScope.launch {
            //  isFetching = true
            _isLoading.value = true
            try {
                val response = charactersListUseCase(currentPage)
                val newList = _charactersList.value.orEmpty() + response.results
                _charactersList.value = newList
            } catch (e: Exception) {
                Log.e("characters", "Error al obtener personajes: ${e.message}")
            }
            //  isFetching = false
            _isLoading.value = false
        }
    }
}
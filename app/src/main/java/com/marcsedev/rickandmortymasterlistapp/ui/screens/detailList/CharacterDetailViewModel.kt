package com.marcsedev.rickandmortymasterlistapp.ui.screens.detailList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.network.CharacterService
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {

    private val repository = CharacterService()

    private val _character = MutableLiveData<CharacterData>()
    val character: LiveData<CharacterData> = _character

    private val _characterId = MutableLiveData<Int>()
    val characterId: LiveData<Int> = _characterId

    init {
        character.value?.let { getCharacterById(id = it.id) }
    }

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            try {
                val character = repository.getCharacter(id)
                _character.value = character
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
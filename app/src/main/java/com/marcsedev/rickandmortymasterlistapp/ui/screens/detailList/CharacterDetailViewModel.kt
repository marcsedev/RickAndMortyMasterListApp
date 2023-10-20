package com.marcsedev.rickandmortymasterlistapp.ui.screens.detailList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcsedev.rickandmortymasterlistapp.data.model.characters.CharacterDetailData
import com.marcsedev.rickandmortymasterlistapp.data.respository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _character = MutableLiveData<CharacterDetailData>()
    val character: LiveData<CharacterDetailData> = _character

    private val _characterId = MutableLiveData<Int>()
    val characterId: LiveData<Int> = _characterId

    init {
        character.value?.let { getCharacterById(id = it.id) }
    }

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            try {
                val character = repository.getCharacterById(id)
                _character.value = character
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
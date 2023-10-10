package com.marcsedev.rickandmortymasterlistapp.ui.list.detailList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcsedev.rickandmortymasterlistapp.data.network.model.characters.CharacterData
import com.marcsedev.rickandmortymasterlistapp.data.respository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {

    private val repository = CharacterRepository()

    private val _character = MutableLiveData<CharacterData>()
    val character: LiveData<CharacterData> = _character

    init {
        getCharacter(id = 2)
    }

    private fun getCharacter(id: Int) {
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
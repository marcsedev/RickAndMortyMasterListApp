package com.marcsedev.rickandmortymasterlistapp.data.network.model.characters

data class InfoData(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
package com.study.marvelapp.repository

import com.study.marvelapp.network.ApiService

class CharacterRepository(private val apiService: ApiService) {

    suspend fun getCharacters() = apiService.getCharacters().await()
}
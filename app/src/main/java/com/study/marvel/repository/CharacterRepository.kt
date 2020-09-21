package com.study.marvel.repository

import com.study.marvel.network.ApiService

class CharacterRepository(private val apiService: ApiService) {

    suspend fun getCharacters() = apiService.getCharacters().await()
}
package com.study.marvel.network

import com.study.marvel.model.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v1/public/characters")
    fun getCharacters(): Deferred<Response<CharacterResponse>>
}
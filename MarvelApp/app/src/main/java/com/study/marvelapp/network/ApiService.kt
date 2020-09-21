package com.study.marvelapp.network

import com.study.marvelapp.model.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v1/public/characters")
    fun getCharacters(): Deferred<Response<CharacterResponse>>
}
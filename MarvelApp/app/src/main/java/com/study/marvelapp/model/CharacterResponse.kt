package com.study.marvelapp.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data")
    var data: CharacterData
)
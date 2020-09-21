package com.study.marvelapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.marvelapp.model.CharacterResponse
import com.study.marvelapp.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    val characterListLiveData = MutableLiveData<CharacterResponse>()
    val errorLiveData = MutableLiveData<String>()
    val progressLiveData = MutableLiveData<Boolean>()

    fun getCharacters() {
        progressLiveData.postValue(true)
        launch {
            try {
                characterListLiveData.postValue(repository.getCharacters().body())
            } catch (e: Exception) {
                Timber.e(e)
                errorLiveData.postValue("Something went wrong!")
            }
            progressLiveData.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
package com.study.marvel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.marvel.model.CharacterResponse
import com.study.marvel.repository.CharacterRepository
import com.study.marvel.utils.state.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class CharacterViewModel(
    private val repository: CharacterRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _characterListLiveData = MutableLiveData<Resource<CharacterResponse>>()
    val characterListLiveData: LiveData<Resource<CharacterResponse>>
        get() = _characterListLiveData

    val errorLiveData = MutableLiveData<String>()
    val progressLiveData = MutableLiveData<Boolean>()


    fun getCharacters() {
        progressLiveData.postValue(true)
        launch {

            try {
                _characterListLiveData.postValue(
                    Resource.success(repository.getCharacters().body())
                )
            } catch (e: Exception) {
                Timber.e(e)
                errorLiveData.postValue("Algo deu errado!")
            }
            progressLiveData.postValue(false)

        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}
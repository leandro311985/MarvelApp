package com.study.marvelapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.study.marvelapp.network.ApiService
import com.study.marvelapp.network.RequestInterceptor
import com.study.marvelapp.repository.CharacterRepository
import com.study.marvelapp.ui.CharacterViewModel
import com.study.marvelapp.utils.Constants
import com.study.marvelapp.utils.debug
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {

    single { provideHttpLoggingInterceptor() }
    single { provideOkHttp(get()) }
    single { provideRetrofit(get()) }

    factory { CharacterRepository(get()) }

    viewModel { CharacterViewModel(get()) }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.apply {
        debug { addInterceptor(httpLoggingInterceptor) }
        addInterceptor(RequestInterceptor())
    }
    return okHttpClient.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}

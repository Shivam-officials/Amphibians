package com.example.amphibians.DI

import com.example.amphibians.data.remote.AmphibiansApiService
import com.example.amphibians.data.repository.NetworkAmphibianRepository
import com.example.amphibians.domain.remote.AmphibiansRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create


interface AppContainer{
    val amphibiansRepository: AmphibiansRepository
}

class DefaultAppContainer :AppContainer{
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val amphibiansApiService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }


    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibianRepository(amphibiansApiService)
    }
}

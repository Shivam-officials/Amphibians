package com.example.amphibians.data.remote

import com.example.amphibians.data.remote.model.Amphibian
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

interface AmphibiansApiService {

    @GET("amphibians")
    suspend fun getAmphibiansData():List<Amphibian>
}




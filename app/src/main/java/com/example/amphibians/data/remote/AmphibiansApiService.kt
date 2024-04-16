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

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

object AmphibiansApi {
    val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }
}
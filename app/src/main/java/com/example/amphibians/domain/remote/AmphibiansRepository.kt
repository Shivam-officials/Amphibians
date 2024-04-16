package com.example.amphibians.domain.remote

import com.example.amphibians.data.remote.model.Amphibian

interface AmphibiansRepository {

    suspend fun getAmphibiansData():List<Amphibian>
}
package com.example.amphibians.data.repository

import com.example.amphibians.data.remote.AmphibiansApiService
import com.example.amphibians.data.remote.model.Amphibian
import com.example.amphibians.domain.remote.AmphibiansRepository

class NetworkAmphibianRepository(
    private val amphibiansApiService: AmphibiansApiService
):AmphibiansRepository {
    override suspend fun getAmphibiansData(): List<Amphibian> {
        return amphibiansApiService.getAmphibiansData()
    }
}
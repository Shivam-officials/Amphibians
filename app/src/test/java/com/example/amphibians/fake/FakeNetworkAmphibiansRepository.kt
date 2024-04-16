package com.example.amphibians.fake

import com.example.amphibians.data.remote.model.Amphibian
import com.example.amphibians.data.repository.NetworkAmphibianRepository
import com.example.amphibians.domain.remote.AmphibiansRepository

class FakeNetworkAmphibiansRepository:AmphibiansRepository {
    override suspend fun getAmphibiansData(): List<Amphibian> {
        return FakeAmphibiansApiService().getAmphibiansData()
    }
}
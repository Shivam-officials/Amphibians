package com.example.amphibians.fake

import com.example.amphibians.data.remote.AmphibiansApiService
import com.example.amphibians.data.remote.model.Amphibian

class FakeAmphibiansApiService : AmphibiansApiService {
    override suspend fun getAmphibiansData(): List<Amphibian> {
        return FakeDataSource.fakeAmphibians
    }
}
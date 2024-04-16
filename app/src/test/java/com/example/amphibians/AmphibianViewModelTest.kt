package com.example.amphibians

import com.example.amphibians.Rules.TestDispatcherRule
import com.example.amphibians.domain.remote.AmphibiansRepository
import com.example.amphibians.fake.FakeDataSource
import com.example.amphibians.fake.FakeNetworkAmphibiansRepository
import com.example.amphibians.presentation.AmphibiansNetworkResponse
import com.example.amphibians.presentation.AmphibiansViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class AmphibianViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun amphibianViewModel_getAmphibiansData_verifyNetWorkResponseDataSuccess() = runTest{

        val fakeRepository : AmphibiansRepository = FakeNetworkAmphibiansRepository()

        val amphibiansViewModel = AmphibiansViewModel(fakeRepository)

        assertEquals(
           AmphibiansNetworkResponse.Success( FakeDataSource.fakeAmphibians),
            amphibiansViewModel.amphibianNetworkResponse
        )

    }
}
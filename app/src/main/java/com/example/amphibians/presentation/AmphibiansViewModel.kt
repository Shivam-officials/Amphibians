package com.example.amphibians.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.remote.model.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException
import com.example.amphibians.data.repository.NetworkAmphibianRepository
import com.example.amphibians.domain.remote.AmphibiansRepository

sealed interface AmphibiansNetworkResponse{
    data class Success(val amphibians: List<Amphibian>) : AmphibiansNetworkResponse
    data object Loading : AmphibiansNetworkResponse
    data object Error : AmphibiansNetworkResponse
}

class AmphibiansViewModel(
    private val amphibiansRepository: AmphibiansRepository
):ViewModel(
) {

    var amphibianNetworkResponse : AmphibiansNetworkResponse by  mutableStateOf(AmphibiansNetworkResponse.Loading)
        private set

    init{
        getAmphibiansData()
    }
    fun getAmphibiansData() {


        viewModelScope.launch {
            amphibianNetworkResponse = try{
               val result =  amphibiansRepository.getAmphibiansData()
                AmphibiansNetworkResponse.Success(result)
            }catch (e:IOException){
                    AmphibiansNetworkResponse.Error
            }
        }
    }

    companion object{
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val amphibiansRepository = (this[APPLICATION_KEY] as AmphibiansApplication).appContainer.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }

}


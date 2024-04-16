package com.example.amphibians.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.data.remote.model.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException
import com.example.amphibians.data.remote.AmphibiansApi

sealed interface AmphibiansNetworkResponse{
    data class Success(val amphibians: List<Amphibian>) : AmphibiansNetworkResponse
    data object Loading : AmphibiansNetworkResponse
    data object Error : AmphibiansNetworkResponse
}

class AmphibiansViewModel:ViewModel() {

    var amphibianNetworkResponse : AmphibiansNetworkResponse by  mutableStateOf(AmphibiansNetworkResponse.Loading)
        private set

    init{
        getAmphibiansData()
    }
    fun getAmphibiansData() {
        viewModelScope.launch {
            amphibianNetworkResponse = try{
               val result =  AmphibiansApi.retrofitService.getAmphibiansData()
                AmphibiansNetworkResponse.Success(result)
            }catch (e:IOException){
                    AmphibiansNetworkResponse.Error
            }
        }
    }

}


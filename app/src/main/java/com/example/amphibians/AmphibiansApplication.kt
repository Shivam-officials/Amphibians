package com.example.amphibians

import android.app.Application
import com.example.amphibians.DI.AppContainer
import com.example.amphibians.DI.DefaultAppContainer

class AmphibiansApplication: Application() {

     lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
         appContainer = DefaultAppContainer()
    }
}
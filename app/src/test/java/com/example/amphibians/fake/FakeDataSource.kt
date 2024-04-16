package com.example.amphibians.fake

import com.example.amphibians.data.remote.model.Amphibian

object FakeDataSource {
    val fakeAmphibians = listOf(
        Amphibian(
            "Great Basin Spadefoot",
            "Toad",
            "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
            "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        )
    )
}
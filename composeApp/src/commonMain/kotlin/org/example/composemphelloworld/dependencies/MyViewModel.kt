package org.example.composemphelloworld.dependencies

import androidx.lifecycle.ViewModel

class MyViewModel(private val repository: MyRepository): ViewModel() {

    fun getPlatformName(): String {
        return repository.getPlatformName()
    }

}
package com.krishnanand.peleton.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class InjectableViewModelFactory @Inject constructor(
    private val mapOfViewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider: Provider<ViewModel>? = mapOfViewModels[modelClass]
        viewModelProvider ?: throw IllegalArgumentException("No binding found")
        return viewModelProvider.get() as T
    }
}
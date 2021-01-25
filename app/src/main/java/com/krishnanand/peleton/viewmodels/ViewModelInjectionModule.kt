package com.krishnanand.peleton.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.Multibinds
import javax.inject.Provider

@Module
interface ViewModelInjectionModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: InjectableViewModelFactory): ViewModelProvider.Factory
}
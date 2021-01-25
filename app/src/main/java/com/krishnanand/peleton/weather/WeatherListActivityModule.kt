package com.krishnanand.peleton.weather

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.krishnanand.peleton.viewmodels.ViewModelInjectionModule
import com.krishnanand.peleton.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelInjectionModule::class,
        WeatherApiModule::class
    ]
)
interface WeatherListActivityModule {

    @Binds
    fun bindWeatherListActivity(weatherListActivity: WeatherListActivity): AppCompatActivity

    @[ViewModelKey(WeatherViewModel::class) IntoMap Binds]
    fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel
}
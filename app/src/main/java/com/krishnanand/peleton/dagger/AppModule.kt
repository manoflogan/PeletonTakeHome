package com.krishnanand.peleton.dagger

import com.krishnanand.peleton.weather.WeatherListActivity
import com.krishnanand.peleton.weather.WeatherListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppModule {

    @ContributesAndroidInjector(modules = [WeatherListActivityModule::class])
    fun provideWeatherListActivityModule(): WeatherListActivity
}
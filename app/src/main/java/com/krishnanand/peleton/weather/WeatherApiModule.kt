package com.krishnanand.peleton.weather


import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object WeatherApiModule {

    @Provides
    fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApiService::class.java)
}
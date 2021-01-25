package com.krishnanand.peleton.dagger

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.krishnanand.peleton.weather.WeatherApiModule
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object ApiModule {

    @Singleton
    @Provides
    internal fun provideGson() = GsonBuilder().create()

    /**
     * Finds the 50MB size
     */
    @Singleton
    @Provides
    internal fun provideOkhttpCache(application: Application): Cache =
        Cache(File(application.cacheDir, "http-cache"),  50L * 1024L * 1024L)

    @Singleton
    @Provides
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .cache(cache)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://www.metaweather.com/api/location/")
            .client(okHttpClient)
            .build()
}
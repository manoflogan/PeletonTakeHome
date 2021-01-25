package com.krishnanand.peleton.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class WeatherViewModel @Inject constructor(
    private val retrofit: Retrofit
): ViewModel() {

    val weatherApiService = retrofit.create(WeatherApiService::class.java)

    private val _locationLiveData: MutableLiveData<Location>  = MutableLiveData<Location>()

    val locationLiveData: LiveData<Location>
       get() = _locationLiveData

    private val _consolidatedWeather: MutableLiveData<ConsolidatedWeather> = MutableLiveData()

    val consolidatedWeather: LiveData<ConsolidatedWeather>
        get() = _consolidatedWeather


    internal fun fetchLocationData(cityName: String) {
        viewModelScope.launch {
            weatherApiService.fetchLocation(cityName).enqueue(object: Callback<List<Location>> {
                override fun onResponse(call: Call<List<Location>>, response: Response<List<Location>>) {
                    _locationLiveData.postValue(response.body()?.let {
                        it[0]
                    })
                }

                override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                    t.printStackTrace()
                    _locationLiveData.value = null
                }
            });
        }
    }

    internal fun fetchConsolidatedWeather(woeid: Long) {
        viewModelScope.launch {
            weatherApiService.fetchConsolidatedWeather(woeid).enqueue(object: Callback<ConsolidatedWeather> {

                override fun onResponse(
                    call: Call<ConsolidatedWeather>,
                    response: Response<ConsolidatedWeather>
                ) {
                    _consolidatedWeather.postValue(response.body())
                }

                override fun onFailure(call: Call<ConsolidatedWeather>, t: Throwable) {
                   _consolidatedWeather.value = null
                }
            })
        }
    }
}
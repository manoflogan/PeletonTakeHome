package com.krishnanand.peleton.weather

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherApiService {


    @GET("search")
    fun fetchLocation(@Query("query") cityName: String): Call<List<Location>>

    @GET("{woeid}")
    fun fetchConsolidatedWeather(@Path("woeid") woeId: Long): Call<ConsolidatedWeather>

}

public data class Location(
    private val title: String,
    @SerializedName("location_type")
    private val locationType: String,
    @SerializedName("woeid")
    val woeId: Long
)

public data class Weather(
    @SerializedName("applicable_date")
    val applicableDate: String,
    @SerializedName("max_temp")
    val maxTemp: Float,
    @SerializedName("min_temp")
    val minTemp: Float
)

public data class ConsolidatedWeather(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<Weather>
)
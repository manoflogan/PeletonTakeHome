package com.krishnanand.peleton.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krishnanand.peleton.R
import com.krishnanand.peleton.databinding.WeatherItemBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WeatherListActivity : AppCompatActivity(), HasAndroidInjector {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val weatherViewModel: WeatherViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weatherListAdapter = WeatherListAdapter(weatherViewModel)
        weatherViewModel.consolidatedWeather.observe(this, {
            it.consolidatedWeather.sortedBy { weather: Weather -> weather.applicableDate }
            weatherListAdapter.setWeatherItems(it.consolidatedWeather)
            weatherListAdapter.notifyDataSetChanged()
        })
        weatherViewModel.locationLiveData.observe(this,  {
            weatherViewModel.fetchConsolidatedWeather(it.woeId)
        })
        weatherViewModel.fetchLocationData("new york")
        with(recyclerView) {
            val linearLayoutManager = LinearLayoutManager(this@WeatherListActivity)
            layoutManager = linearLayoutManager
            adapter = weatherListAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation))
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}

internal class WeatherViewHolder(private val weatherItemBinding: WeatherItemBinding): RecyclerView.ViewHolder(weatherItemBinding.root) {

    fun bindWeather(weather: Weather, viewModel: WeatherViewModel) {
        weatherItemBinding.weather = weather;
        weatherItemBinding.viewModel = viewModel
        weatherItemBinding.executePendingBindings()
    }
}

internal class WeatherListAdapter(private val weatherViewModel: WeatherViewModel): RecyclerView.Adapter<WeatherViewHolder>() {
    private val weatherList: MutableList<Weather> = mutableListOf()

    fun setWeatherItems(weatherList: List<Weather>) {
        this.weatherList.addAll(weatherList)
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindWeather(weatherList[position], weatherViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: WeatherItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.weather_item, parent, false);
        return WeatherViewHolder(binding)
    }
}
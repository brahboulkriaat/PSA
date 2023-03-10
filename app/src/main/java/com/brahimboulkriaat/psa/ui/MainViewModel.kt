package com.brahimboulkriaat.psa.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.model.Weather
import com.brahimboulkriaat.psa.repository.CityRepository
import com.brahimboulkriaat.psa.repository.WeatherRepository
import com.brahimboulkriaat.psa.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val fabOnClick = mutableStateOf<() -> Unit>({})
    val isFabExpanded = mutableStateOf<Boolean>(true)

    private val _citiesState: MutableStateFlow<DataState<List<City>>> =
        MutableStateFlow(DataState.Loading)
    val citiesState: StateFlow<DataState<List<City>>> = _citiesState

    private val _weatherState: MutableStateFlow<DataState<Weather>> =
        MutableStateFlow(DataState.Loading)
    val weatherState: StateFlow<DataState<Weather>> = _weatherState

    private val _newCityState: MutableStateFlow<DataState<City>> =
        MutableStateFlow(DataState.None)
    val newCityState: StateFlow<DataState<City>> get() = _newCityState

    fun getCities() {
        viewModelScope.launch {
            cityRepository.getAll().onEach { _citiesState.value = it }.launchIn(viewModelScope)
        }
    }

    fun getWeather(lon: Float, lat: Float) {
        viewModelScope.launch {
            weatherRepository.get(lon, lat).onEach {
                _weatherState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun createNewCity(name: String, lon: Double, lat: Double) = viewModelScope.launch {
        cityRepository.create(City(id = 0, name = name, lon = lon, lat = lat))
            .onEach { _newCityState.value = it }.launchIn(viewModelScope)
    }

    fun setCitiesStateToLoading() {
        _citiesState.update {
            DataState.Loading
        }
    }

    fun setWeatherStateToLoading() {
        _weatherState.update {
            DataState.Loading
        }
    }

}
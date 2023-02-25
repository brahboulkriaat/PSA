package com.brahimboulkriaat.psa.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.repository.CityRepository
import com.brahimboulkriaat.psa.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {

    val fabOnClick = mutableStateOf<() -> Unit>({})
    val itemOnClick = mutableStateOf<() -> Unit>({})
    val isFabExpanded = mutableStateOf<Boolean>(true)

    val cities = mutableStateOf<List<City>>(listOf())

    private val _getAllResponseState: MutableLiveData<DataState<List<City>>> = MutableLiveData()
    val getAllResponseState: LiveData<DataState<List<City>>> get() = _getAllResponseState

    private val _createResponseState: MutableLiveData<DataState<City>> = MutableLiveData()
    val createResponseState: LiveData<DataState<City>> get() = _createResponseState

    fun launchRequest() {
        viewModelScope.launch {
            cityRepository.getAll().onEach { _getAllResponseState.value = it }.launchIn(viewModelScope)
        }
    }

    fun createNewCity(name: String, lon: Double, lat: Double) = viewModelScope.launch {
        cityRepository.create(City(id = 0, name = name, lon = lon, lat = lat)).onEach { _createResponseState.value = it }.launchIn(viewModelScope)
    }
}
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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {

    val fabOnClick = mutableStateOf<() -> Unit>({})
    val itemOnClick = mutableStateOf<() -> Unit>({})
    val isFabExpanded = mutableStateOf<Boolean>(true)

    private val _citiesState: MutableStateFlow<DataState<List<City>>> = MutableStateFlow(DataState.Loading)
    val citiesState: StateFlow<DataState<List<City>>> = _citiesState.asStateFlow()

    private val _createResponseState: MutableLiveData<DataState<City>> = MutableLiveData()
    val createResponseState: LiveData<DataState<City>> get() = _createResponseState

    fun launchRequest() {
        viewModelScope.launch {
            cityRepository.getAll().onEach { _citiesState.value = it }.launchIn(viewModelScope)
        }
    }

    fun createNewCity(name: String, lon: Double, lat: Double) = viewModelScope.launch {
        cityRepository.create(City(id = 0, name = name, lon = lon, lat = lat)).onEach { _createResponseState.value = it }.launchIn(viewModelScope)
    }

    fun setCitiesStateToLoading() {
        _citiesState.update {
            DataState.Loading
        }
    }

}
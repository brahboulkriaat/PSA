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

    private val _responseState: MutableLiveData<DataState<List<City>>> = MutableLiveData()
    val responseState: LiveData<DataState<List<City>>> get() = _responseState

    fun launchRequest() {
        viewModelScope.launch {
            cityRepository.getAll().onEach { _responseState.value = it }.launchIn(viewModelScope)
        }
    }
}
package com.brahimboulkriaat.psa.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen(mainViewModel)
            }
        }

        mainViewModel.getAllResponseState.observe(this) {
            when (it) {
                is DataState.Success<List<City>> -> { //TODO: Fix me
                    Log.d(TAG, "Success getAll : ${it.data}")
                    mainViewModel.cities.value = it.data
                    // displayProgressBar(false)
                    // appendBlogTitles(it.result)
                }
                is DataState.Error -> {
                    Log.d(TAG, "Error getAll : ${it.error.message}")
                    // displayProgressBar(false)
                    // displayError(it.error.message)
                }
                else -> {
                    Log.d(TAG, "Loading getAll")
                    // displayProgressBar(true)
                }
            }
        }

        mainViewModel.createResponseState.observe(this) {
            when (it) {
                is DataState.Success<City> -> { //TODO: Fix me
                    Log.d(TAG, "Success create : ${it.data}")
                }
                is DataState.Error -> {
                    Log.d(TAG, "Error create : ${it.error.message}")
                }
                else -> {
                    Log.d(TAG, "Loading create")
                }
            }
        }

        mainViewModel.launchRequest()
    }
}




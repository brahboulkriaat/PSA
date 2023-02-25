package com.brahimboulkriaat.psa.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

        mainViewModel.responseState.observe(this) {
            when (it) {
                is DataState.Success<List<City>> -> { //TODO: Fix me
                    Log.d(TAG, "Success : ${it.data}")
                    mainViewModel.cities.value = it.data
                    // displayProgressBar(false)
                    // appendBlogTitles(it.result)
                }
                is DataState.Error -> {
                    Log.d(TAG, "Error : ${it.error.message}")
                    // displayProgressBar(false)
                    // displayError(it.error.message)
                }
                else -> {
                    Log.d(TAG, "Loading")
                    // displayProgressBar(true)
                }
            }
        }

        mainViewModel.launchRequest()
    }
}




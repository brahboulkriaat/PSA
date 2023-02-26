package com.brahimboulkriaat.psa.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.ui.MainViewModel
import com.brahimboulkriaat.psa.ui.component.CitiesComponent
import com.brahimboulkriaat.psa.ui.component.ErrorComponent
import com.brahimboulkriaat.psa.ui.component.LoaderComponent
import com.brahimboulkriaat.psa.util.DataState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Cities") }) },
        floatingActionButton = { NewCityFab(mainViewModel) }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            mainViewModel.run {
                getCities()
                val citiesState = citiesState.collectAsStateWithLifecycle(DataState.Loading)
                when (citiesState.value) {
                    is DataState.Loading -> LoaderComponent()
                    is DataState.Error -> ErrorComponent(
                        onRetryClick = {
                            setCitiesStateToLoading()
                            getCities()
                        }
                    )
                    is DataState.Success<List<City>> -> {
                        CitiesComponent(
                            cities = (citiesState.value as DataState.Success<List<City>>).data,
                            onItemClick = { city -> navController.navigate("details/${city.lon}/${city.lat}") }
                        )
                    }
                }
            }
            val listState = rememberLazyListState()
            val expandedFabState = remember {
                derivedStateOf {
                    listState.firstVisibleItemIndex == 0
                }
            }

            LaunchedEffect(
                key1 = expandedFabState.value,
                block = {
                    mainViewModel.isFabExpanded.value = expandedFabState.value
                })

            LaunchedEffect(
                key1 = Unit,
                block = {
                    mainViewModel.fabOnClick.value =
                        {
                            //Toast.makeText(context, "New City", Toast.LENGTH_SHORT).show()
                            navController.navigate("new")
                        }
                })
        }
    }
}


@Composable
private fun NewCityFab(mainViewModel: MainViewModel) {
    ExtendedFloatingActionButton(
        modifier = Modifier.padding(all = 8.dp),
        text = { Text(text = "New City") },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Navigate FAB"

            )
        },
        expanded = mainViewModel.isFabExpanded.value,
        onClick = { mainViewModel.fabOnClick.value.invoke() },
        // containerColor = MaterialTheme.colorScheme.secondary
    )
}
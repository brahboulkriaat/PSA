package com.brahimboulkriaat.psa.ui

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
import com.brahimboulkriaat.psa.model.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel, navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Cities") }) },
        floatingActionButton = { NewCityFab(mainViewModel) }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            val context = LocalContext.current
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

            LaunchedEffect(
                key1 = Unit,
                block = {
                    mainViewModel.itemOnClick.value =
                        {
                            // Toast.makeText(context, "Details", Toast.LENGTH_SHORT).show()
                            navController.navigate("details")
                        }
                })

            CitiesList(listState, mainViewModel)
        }
    }
}

@Composable
private fun CitiesList(
    state: LazyListState,
    mainViewModel: MainViewModel
) {
    LazyColumn(state = state, modifier = Modifier.fillMaxSize()) {
        items(mainViewModel.cities.value) { city ->
            CityItem(city, mainViewModel)
        }
    }
}

@Composable
private fun CityItem(city: City, mainViewModel: MainViewModel) {
    ElevatedCard(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .clickable { mainViewModel.itemOnClick.value.invoke() }
    ) {
        Surface(modifier = Modifier.padding(all = 8.dp)) {
            Text(text = city.name)
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
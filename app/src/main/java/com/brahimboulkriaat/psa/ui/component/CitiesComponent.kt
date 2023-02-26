package com.brahimboulkriaat.psa.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.brahimboulkriaat.psa.model.City

@Composable
fun CitiesComponent(
    cities: List<City>,
    onItemClick: (city: City) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cities) { city ->
            CityItemComponent(
                city = city,
                onClick = onItemClick
            )
        }
    }
}

@Composable
private fun CityItemComponent(
    city: City,
    onClick: (city: City) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke(city) }
    ) {
        Surface(modifier = Modifier.padding(all = 8.dp)) {
            Text(text = city.name)
        }
    }
}
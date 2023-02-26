package com.brahimboulkriaat.psa.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brahimboulkriaat.psa.model.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityTypeComponent(
    modifier: Modifier,
    name : String,
    lon : String,
    lat : String,
    onNameChange: (text : TextFieldValue) -> Unit,
    onLonChange: (text : TextFieldValue) -> Unit,
    onLatChange: (text : TextFieldValue) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add a new city",
            style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            label = { Text("Name") },
            value = TextFieldValue(name),
            onValueChange = onNameChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Longitude") },
            value = TextFieldValue(lon),
            onValueChange = onLonChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = { Text("Latitude") },
            value = TextFieldValue(lat),
            onValueChange = onLatChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
    }
}
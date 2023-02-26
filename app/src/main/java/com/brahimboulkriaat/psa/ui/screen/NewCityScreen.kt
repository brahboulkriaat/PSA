package com.brahimboulkriaat.psa.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.brahimboulkriaat.psa.model.City
import com.brahimboulkriaat.psa.ui.CustomTopAppBar
import com.brahimboulkriaat.psa.ui.MainViewModel
import com.brahimboulkriaat.psa.ui.component.AddCityTypeComponent
import com.brahimboulkriaat.psa.util.DataState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCityScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CustomTopAppBar(
            navController = navController,
            title = "New City",
            showBackIcon = true
        )
    }, content = {
        Column(
            modifier = Modifier.padding(it).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val name = remember {
                mutableStateOf(TextFieldValue())
            }

            val lon = remember {
                mutableStateOf(TextFieldValue())
            }

            val lat = remember {
                mutableStateOf(TextFieldValue())
            }

            Text(
                text = "Add a new city",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                label = { Text("Name") },
                value = name.value,
                onValueChange = { value -> name.value = value })

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                label = { Text("Longitude") },
                value = lon.value,
                onValueChange = { value -> lon.value = value },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                label = { Text("Latitude") },
                value = lat.value,
                onValueChange = { value -> lat.value = value },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(modifier = Modifier.height(24.dp))

            /*Button(
                onClick = {},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ){
                Text(text = "Send")
            }*/

            mainViewModel.run {
                val newCityState = newCityState.collectAsStateWithLifecycle(DataState.None)
                when (newCityState.value) {
                    is DataState.None -> {
                        AddButton(
                            isSending = false) {
                            mainViewModel.createNewCity(
                                name.value.text,
                                lon.value.text.toDouble(),
                                lat.value.text.toDouble()
                            )
                        }
                    }
                    is DataState.Loading -> {
                        AddButton(isSending = true) {}
                    }
                    is DataState.Error -> {
                        AddButton(isSending = false) {
                            mainViewModel.createNewCity(
                                name.value.text,
                                lon.value.text.toDouble(),
                                lat.value.text.toDouble()
                            )
                        }
                        Toast.makeText(LocalContext.current, "Erreur", Toast.LENGTH_SHORT).show()
                    }
                    is DataState.Success<City> -> {
                        navController.popBackStack("home", inclusive = false)
                        Toast.makeText(LocalContext.current, "Sucess", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    })
}

@Composable
fun AddButton(
    isSending: Boolean,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        if (isSending) CircularProgressIndicator() else Button(
            onClick = onClick,
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Add")
        }
    }
}
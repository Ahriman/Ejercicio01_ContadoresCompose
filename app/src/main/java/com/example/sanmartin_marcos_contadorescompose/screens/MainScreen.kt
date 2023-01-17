package com.example.sanmartin_marcos_contadorescompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sanmartin_marcos_contadorescompose.navigation.Screens

@Composable
fun MainScreen(navController: NavController) {
    // Vertical y horizontal son iguales
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState()),
            .padding(top = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate(Screens.Ejercicio01.route)
        }) {
            Text(text = "Ejercicio 1")
        }
    }
}
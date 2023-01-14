package com.example.sanmartin_marcos_contadorescompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sanmartin_marcos_contadorescompose.screens.ejercicios.Ej01Screen

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
        var contador1 by rememberSaveable { mutableStateOf(0) }
        var contador2 by rememberSaveable { mutableStateOf(0) }

        var incremento1 by rememberSaveable { mutableStateOf("1") }
        var incremento2 by rememberSaveable { mutableStateOf("2") }

        var contadorGlobal by rememberSaveable { mutableStateOf(0) }

        Ej01Screen()
    }
}
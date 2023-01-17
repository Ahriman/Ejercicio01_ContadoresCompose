package com.example.sanmartin_marcos_contadorescompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sanmartin_marcos_contadorescompose.screens.MainScreen
import com.example.sanmartin_marcos_contadorescompose.screens.ejercicios.Ej01Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Ejercicio01.route) {// Pantalla principal el Ejercicio01 (No se usa MainScreen dónde elegir el ejercicio correspondiente)

        composable(route = Screens.MainScreen.route) { MainScreen(navController) }

        composable(route = Screens.Ejercicio01.route) { Ej01Screen() }

    }
}
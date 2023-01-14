package com.example.sanmartin_marcos_contadorescompose.navigation

sealed class Screens(val route: String) {

    object MainScreen: Screens("initial_screen")

    object Ejercicio01: Screens("ej01")

}
package com.example.pc1movilesquispe23200068mendieta23200161

sealed class Screen(val route: String) {
    object Menu : Screen("menu_screen")
    object Equipaje : Screen("equipaje_screen")
    object Presupuesto : Screen("presupuesto_screen")
    object Catalogo : Screen("catalogo_screen")
    object Permisos : Screen("permisos_screen")
}

// Aprovechamos en dejar creado el molde para la pantalla 3
data class Destination(
    val id: Int,
    val pais: String,
    val ciudad: String,
    val costoPromedio: Double,
    val urlImagen: String
)
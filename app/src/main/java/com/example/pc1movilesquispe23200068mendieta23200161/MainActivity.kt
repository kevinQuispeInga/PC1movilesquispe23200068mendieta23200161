package com.example.pc1movilesquispe23200068mendieta23200161

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// ==========================================
// ACTIVIDAD PRINCIPAL (PUNTO DE ENTRADA)
// ==========================================
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TravelAppNavigation()
                }
            }
        }
    }
}

// ==========================================
// ENRUTADOR PRINCIPAL (NAVHOST)
// ==========================================
@Composable
fun TravelAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Menu.route // Inicia en Pantalla 0
    ) {
        // PANTALLA 0: Menú Principal
        composable(Screen.Menu.route) {
            MenuScreen(navController = navController)
        }

        // Contenedores temporales para evitar errores de navegación
        composable(Screen.Equipaje.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Pantalla 1: Calculadora de Equipaje")
            }
        }
        composable(Screen.Presupuesto.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Pantalla 2: Planificador de Presupuesto")
            }
        }
        composable(Screen.Catalogo.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Pantalla 3: Catálogo de Destinos")
            }
        }
        composable(Screen.Permisos.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Pantalla 4: Permiso de Ubicación")
            }
        }
    }
}

// ==========================================
// PANTALLA 0: MENÚ PRINCIPAL (BOTONES ROJOS)
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Travel Companion App") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Menú Principal",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Botón 1: Calculadora de Equipaje
            Button(
                onClick = { navController.navigate(Screen.Equipaje.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Calculadora de Equipaje")
            }

            // Botón 2: Planificador de Presupuesto de Viaje
            Button(
                onClick = { navController.navigate(Screen.Presupuesto.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Planificador de Presupuesto de Viaje")
            }

            // Botón 3: Catálogo de Destinos Turísticos
            Button(
                onClick = { navController.navigate(Screen.Catalogo.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Catálogo de Destinos Turísticos")
            }

            // Botón 4: Permiso de Ubicación
            Button(
                onClick = { navController.navigate(Screen.Permisos.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Permiso de Ubicación para Asistencia de Viaje")
            }
        }
    }
}
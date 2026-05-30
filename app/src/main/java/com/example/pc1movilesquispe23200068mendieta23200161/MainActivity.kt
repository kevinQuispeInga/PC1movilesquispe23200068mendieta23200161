package com.example.pc1movilesquispe23200068mendieta23200161

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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

        // PANTALLA 1: Calculadora de Equipaje
        composable(Screen.Equipaje.route) {
            CalculadoraEquipajeScreen()
        }

        // Contenedores temporales para el resto de pantallas (Evita errores de compilación)
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

// ==========================================
// PANTALLA 1: CALCULADORA DE EQUIPAJE (3 PTS)
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraEquipajeScreen() {
    var pesoInput by remember { mutableStateOf("") }
    var esInternacional by remember { mutableStateOf(false) } // false = Nacional, true = Internacional
    var resultadoMsg by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Calculadora de Equipaje") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Control de Peso de Maleta",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Entrada de Texto para el peso
            OutlinedTextField(
                value = pesoInput,
                onValueChange = { pesoInput = it },
                label = { Text("Peso de la maleta (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = errorMsg.isNotEmpty()
            )

            if (errorMsg.isNotEmpty()) {
                Text(
                    text = errorMsg,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Seleccione el tipo de vuelo:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            // RadioButtons para Tipo de Vuelo
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = !esInternacional,
                    onClick = { esInternacional = false }
                )
                Text("Nacional (Máx 23 kg)", modifier = Modifier.padding(end = 16.dp))

                RadioButton(
                    selected = esInternacional,
                    onClick = { esInternacional = true }
                )
                Text("Internacional (Máx 32 kg)")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de Procesamiento
            Button(
                onClick = {
                    val peso = pesoInput.toDoubleOrNull()

                    // Validaciones obligatorias de la rúbrica
                    if (pesoInput.isBlank()) {
                        errorMsg = "El campo es obligatorio."
                        resultadoMsg = ""
                    } else if (peso == null) {
                        errorMsg = "Debe ingresar un valor numérico válido."
                        resultadoMsg = ""
                    } else if (peso <= 0) {
                        errorMsg = "El peso debe ser mayor a cero."
                        resultadoMsg = ""
                    } else {
                        errorMsg = ""
                        val limitePermitido = if (esInternacional) 32 else 23

                        // Lógica de visualización de resultados
                        if (peso <= limitePermitido) {
                            resultadoMsg = "✅ ¡Cumple con el límite permitido! Su maleta pesa $peso kg (Límite: $limitePermitido kg)."
                        } else {
                            val kgExcedidos = peso - limitePermitido
                            resultadoMsg = "❌ Excede el límite permitido.\nKilogramos excedidos: ${String.format("%.2f", kgExcedidos)} kg."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Calcular Límite")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Bloque de Visualización de Resultados
            if (resultadoMsg.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text(
                        text = resultadoMsg,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
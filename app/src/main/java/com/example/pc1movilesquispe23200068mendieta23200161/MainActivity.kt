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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
        startDestination = Screen.Menu.route
    ) {
        // PANTALLA 0: Menú Principal
        composable(Screen.Menu.route) {
            MenuScreen(navController = navController)
        }

        // PANTALLA 1: Calculadora de Equipaje
        composable(Screen.Equipaje.route) {
            CalculadoraEquipajeScreen()
        }

        // PANTALLA 2: Planificador de Presupuesto de Viaje
        composable(Screen.Presupuesto.route) {
            PlanificadorPresupuestoScreen()
        }

        // Contenedores temporales restantes
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
// PANTALLA 0: MENÚ PRINCIPAL
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Travel Companion App") }) }
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

            Button(
                onClick = { navController.navigate(Screen.Equipaje.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Calculadora de Equipaje")
            }

            Button(
                onClick = { navController.navigate(Screen.Presupuesto.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Planificador de Presupuesto de Viaje")
            }

            Button(
                onClick = { navController.navigate(Screen.Catalogo.route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Catálogo de Destinos Turísticos")
            }

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
// PANTALLA 1: CALCULADORA DE EQUIPAJE
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculadoraEquipajeScreen() {
    var pesoInput by remember { mutableStateOf("") }
    var esInternacional by remember { mutableStateOf(false) }
    var resultadoMsg by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Calculadora de Equipaje") }) }
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
            Text(text = "Seleccione el tipo de vuelo:", fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth())

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = !esInternacional, onClick = { esInternacional = false })
                Text("Nacional (Máx 23 kg)", modifier = Modifier.padding(end = 16.dp))

                RadioButton(selected = esInternacional, onClick = { esInternacional = true })
                Text("Internacional (Máx 32 kg)")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val peso = pesoInput.toDoubleOrNull()
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
            if (resultadoMsg.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text(text = resultadoMsg, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

// ==========================================
// PANTALLA 2: PLANIFICADOR DE PRESUPUESTO (4 PTS)
// ==========================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanificadorPresupuestoScreen() {
    // Estados para inputs y lógica
    var diasInput by remember { mutableStateOf("") }
    var presupuestoDiarioInput by remember { mutableStateOf("") }
    var tipoAlojamiento by remember { mutableStateOf("Estándar") }
    var factorAlojamiento by remember { mutableStateOf(1.0) }
    var totalResultado by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    // Control del Menú Desplegable
    var menuExpandido by remember { mutableStateOf(false) }
    val opcionesAlojamiento = listOf("Económico (0.8)", "Estándar (1.0)", "Premium (1.5)")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Planificador de Presupuesto") }) }
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
                text = "Cálculo de Presupuesto de Viaje",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Campo 1: Cantidad de Días
            OutlinedTextField(
                value = diasInput,
                onValueChange = { diasInput = it },
                label = { Text("Cantidad de días") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo 2: Presupuesto Diario
            OutlinedTextField(
                value = presupuestoDiarioInput,
                onValueChange = { presupuestoDiarioInput = it },
                label = { Text("Presupuesto diario ($)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tipo de alojamiento:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
            )

            // Campo 3: Dropdown Menu oficial de Material 3
            ExposedDropdownMenuBox(
                expanded = menuExpandido,
                onExpandedChange = { menuExpandido = !menuExpandido }
            ) {
                OutlinedTextField(
                    value = tipoAlojamiento,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = menuExpandido) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = menuExpandido,
                    onDismissRequest = { menuExpandido = false }
                ) {
                    opcionesAlojamiento.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                tipoAlojamiento = opcion
                                factorAlojamiento = when (opcion) {
                                    "Económico (0.8)" -> 0.8
                                    "Premium (1.5)" -> 1.5
                                    else -> 1.0
                                }
                                menuExpandido = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Mostrar errores de validación
            if (errorMsg.isNotEmpty()) {
                Text(
                    text = errorMsg,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Botón Calcular
            Button(
                onClick = {
                    val dias = diasInput.toIntOrNull()
                    val presupuesto = presupuestoDiarioInput.toDoubleOrNull()

                    // Validaciones estrictas de la rúbrica
                    if (diasInput.isBlank() || presupuestoDiarioInput.isBlank()) {
                        errorMsg = "Todos los campos son obligatorios."
                        totalResultado = ""
                    } else if (dias == null || dias <= 0) {
                        errorMsg = "La cantidad de días debe ser un número entero mayor a cero."
                        totalResultado = ""
                    } else if (presupuesto == null || presupuesto <= 0) {
                        errorMsg = "El presupuesto diario debe ser un valor numérico mayor a cero."
                        totalResultado = ""
                    } else {
                        // Limpieza y cálculo
                        errorMsg = ""
                        val total = dias * presupuesto * factorAlojamiento

                        // Resultado formateado a dos decimales con mensaje descriptivo
                        totalResultado = "💰 Presupuesto Total: $${String.format("%.2f", total)}\n\nEscenario: Viaje planificado para un periodo de $dias días, con un presupuesto asignado por día de $$presupuesto y un hospedaje de categoría $tipoAlojamiento."
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Calcular Presupuesto")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta con el mensaje final obtenido
            if (totalResultado.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = totalResultado,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
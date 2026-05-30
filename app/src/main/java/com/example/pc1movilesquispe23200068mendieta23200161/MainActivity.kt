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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

/**
 * Actividad principal de la aplicación que sirve como punto de entrada.
 * Configura el tema y el sistema de navegación.
 */
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

/**
 * Define el NavHost y las rutas de navegación para la aplicación.
 */
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

        // PANTALLA 3: Catálogo de Destinos Turísticos
        composable(Screen.Catalogo.route) {
            CatalogoDestinosScreen()
        }

        // Contenedor temporal restante
        composable(Screen.Permisos.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Pantalla 4: Permiso de Ubicación")
            }
        }
    }
}

/**
 * Pantalla del Menú Principal que ofrece accesos directos a las distintas funcionalidades.
 * @param navController Controlador de navegación para redirigir al usuario.
 */
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

/**
 * Pantalla de la Calculadora de Equipaje.
 * Permite al usuario verificar si su maleta cumple con los límites de peso establecidos
 * según el tipo de vuelo (Nacional o Internacional).
 */
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

/**
 * Pantalla del Planificador de Presupuesto.
 * Calcula el presupuesto total de un viaje basándose en los días, presupuesto diario
 * y el factor del tipo de alojamiento seleccionado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanificadorPresupuestoScreen() {
    var diasInput by remember { mutableStateOf("") }
    var presupuestoDiarioInput by remember { mutableStateOf("") }
    var tipoAlojamiento by remember { mutableStateOf("Estándar") }
    var factorAlojamiento by remember { mutableStateOf(1.0) }
    var totalResultado by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

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

            OutlinedTextField(
                value = diasInput,
                onValueChange = { diasInput = it },
                label = { Text("Cantidad de días") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = presupuestoDiarioInput,
                onValueChange = { presupuestoDiarioInput = it },
                label = { Text("Presupuesto diario ($)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Tipo de alojamiento:", fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp))

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
            if (errorMsg.isNotEmpty()) {
                Text(text = errorMsg, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                onClick = {
                    val dias = diasInput.toIntOrNull()
                    val presupuesto = presupuestoDiarioInput.toDoubleOrNull()

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
                        errorMsg = ""
                        val total = dias * presupuesto * factorAlojamiento
                        totalResultado = "💰 Presupuesto Total: $${String.format("%.2f", total)}\n\nEscenario: Viaje planificado para un periodo de $dias días, con un presupuesto asignado por día de $$presupuesto y un hospedaje de categoría $tipoAlojamiento."
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Calcular Presupuesto")
            }

            Spacer(modifier = Modifier.height(24.dp))
            if (totalResultado.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(text = totalResultado, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

/**
 * Pantalla del Catálogo de Destinos Turísticos.
 * Muestra una lista de destinos con imágenes cargadas asíncronamente y un resumen estadístico.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoDestinosScreen() {
    // 1. Simulación de la lista obligatoria con al menos 5 destinos de internet
    val destinos = remember {
        listOf(
            Destination(1, "Francia", "París", 150.0, "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?w=500"),
            Destination(2, "Japón", "Tokio", 180.5, "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?w=500"),
            Destination(3, "Italia", "Roma", 125.0, "https://images.unsplash.com/photo-1552832230-c0197dd311b5?w=500"),
            Destination(4, "Perú", "Cusco", 95.5, "https://images.unsplash.com/photo-1587547178031-84c407850814?w=500"),
            Destination(5, "Estados Unidos", "Nueva York", 210.0, "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?w=500")
        )
    }

    // Cálculos de resumen requeridos
    val cantidadTotal = destinos.size
    val costoAcumulado = destinos.sumOf { it.costoPromedio }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Catálogo de Destinos") }) }
    ) { paddingValues ->
        // Utilización estructurada de LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Destinos Recomendados",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            // Mapeo dinámico de los elementos en el LazyColumn
            items(destinos) { destino ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    // Distribución Row para colocar la imagen a la izquierda y texto a la derecha
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Carga asíncrona de Coil con AsyncImage
                        AsyncImage(
                            model = destino.urlImagen,
                            contentDescription = "Imagen de ${destino.ciudad}",
                            modifier = Modifier
                                .size(90.dp)
                                .padding(end = 12.dp),
                            contentScale = ContentScale.Crop
                        )

                        // Column interna para los textos del destino
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${destino.ciudad}, ${destino.pais}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Costo promedio: $${destino.costoPromedio} por día",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // Bloque final con el totalizador acumulado requerido
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "📊 Resumen del Catálogo",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Cantidad total de destinos: $cantidadTotal",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Suma acumulada de costos: $${String.format("%.2f", costoAcumulado)}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
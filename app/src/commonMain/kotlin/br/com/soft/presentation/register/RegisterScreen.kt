package br.com.soft.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import shared.design.component.withPlaceholder
import shared.presentation.viewmodel.provideViewModel


@Composable
fun RegisterScreen() {
    var condominiumName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var apartments by remember { mutableStateOf(listOf<String>()) }
    var newApartment by remember { mutableStateOf("") }
    var areas by remember { mutableStateOf(listOf<String>()) }
    var newArea by remember { mutableStateOf("") }
    val viewModel: RegisterViewModel = provideViewModel()
    val loading = viewModel.loadingState.asStateValueNotNull()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cadastrar",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = condominiumName,
            onValueChange = { condominiumName = it },
            label = { Text("Nome do Condomínio") },
            modifier = Modifier.fillMaxWidth().withPlaceholder(loading, cornerRadius = 8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().withPlaceholder(loading, cornerRadius = 8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = newApartment,
            onValueChange = { newApartment = it },
            label = { Text("Adicionar Apartamento") },
            modifier = Modifier.fillMaxWidth()
                .withPlaceholder(loading, cornerRadius = 8.dp),
        )
        Button(
            onClick = {
                if (newApartment.isNotEmpty()) {
                    apartments = apartments + newApartment
                    newApartment = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
                .withPlaceholder(loading, cornerRadius = 8.dp)
                .fillMaxWidth(),
        ) {
            Text("Adicionar Apartamento")
        }
        if (apartments.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            ApartmentGrid(apartments = apartments, onApartmentChange = { index, newValue ->
                apartments = apartments.toMutableList().apply { set(index, newValue) }
            }, onRemoveApartment = { index ->
                apartments = apartments.toMutableList().apply { removeAt(index) }
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = newArea,
            onValueChange = { newArea = it },
            label = { Text("Adicionar Área Reservável") },
            modifier = Modifier.fillMaxWidth().withPlaceholder(loading, cornerRadius = 8.dp)
                .fillMaxWidth(),
        )
        Button(
            onClick = {
                if (newArea.isNotEmpty()) {
                    areas = areas + newArea
                    newArea = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
                .withPlaceholder(loading, cornerRadius = 8.dp).fillMaxWidth(),
        ) {
            Text("Adicionar Área Reservável")
        }
        if (areas.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            AreaGrid(areas = areas, onAreaChange = { index, newValue ->
                areas = apartments.toMutableList().apply { set(index, newValue) }
            }, onRemoveArea = { index ->
                areas = areas.toMutableList().apply { removeAt(index) }
            })
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Lógica de salvar o condomínio, apartamentos e áreas
                println("Condomínio: $condominiumName")
                println("Email: $email")
                println("Apartamentos: $apartments")
                println("Áreas reserváveis: $areas")
            },
            modifier = Modifier.fillMaxWidth().withPlaceholder(loading, cornerRadius = 8.dp)
                .fillMaxWidth(),
        ) {
            Text("Cadastrar Condomínio")
        }
    }
}

fun getHeightGrid(pListLength: Int): Dp {
    return when {
        pListLength == 0 -> 0.dp
        pListLength <= 2 -> 56.dp
        pListLength <= 4 -> 112.dp
        else -> 170.dp
    }
}

@Composable
fun ApartmentGrid(
    apartments: List<String>,
    onApartmentChange: (Int, String) -> Unit,
    onRemoveApartment: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(getHeightGrid(apartments.size))
    ) {
        items(apartments.size) { index ->
            OutlinedTextField(
                value = apartments[index],
                onValueChange = { newValue -> onApartmentChange(index, newValue) },
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { onRemoveApartment(index) }) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Remover apartamento"
                        )
                    }
                }
            )
        }
    }
}


@Composable
fun AreaGrid(
    areas: List<String>,
    onAreaChange: (Int, String) -> Unit,
    onRemoveArea: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(getHeightGrid(areas.size))
    ) {
        items(areas.size) { index ->
            OutlinedTextField(
                value = areas[index],
                onValueChange = { newValue -> onAreaChange(index, newValue) },
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { onRemoveArea(index) }) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Remover area"
                        )
                    }
                }
            )
        }
    }
}
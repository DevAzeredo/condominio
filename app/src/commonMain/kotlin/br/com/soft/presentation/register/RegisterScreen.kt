package br.com.soft.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import shared.design.component.AppText
import shared.design.component.withPlaceholder
import shared.presentation.viewmodel.provideViewModel




@Composable
fun RegisterScreen() {
    var condominiumName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var apartments by remember { mutableStateOf(listOf<String>()) }
    var newApartment by remember { mutableStateOf("") }
    var areas by remember { mutableStateOf(listOf<String>()) }
    var newArea by remember { mutableStateOf("") }
    val viewModel: RegisterViewModel = provideViewModel()
    val loading = viewModel.loadingState.asStateValueNotNull()
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = condominiumName,
            onValueChange = { condominiumName = it },
            label = { Text("Nome do Condomínio") },
            modifier = Modifier.fillMaxWidth().withPlaceholder(loading, cornerRadius = 8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth().withPlaceholder(loading, cornerRadius = 8.dp),
            visualTransformation = PasswordVisualTransformation() // Esconde a senha
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newApartment,
            onValueChange = { newApartment = it },
            label = { Text("Adicionar Apartamento") },
            modifier = Modifier.fillMaxWidth().withPlaceholder(loading, cornerRadius = 8.dp),
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
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(apartments) {
                Text(it, Modifier.border(1.dp, Color.Blue).height(80.dp).wrapContentSize())
            }
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

        LazyColumn {
            items(areas) { area ->
                Text(area)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Lógica de salvar o condomínio, apartamentos e áreas
                println("Condomínio: $condominiumName")
                println("Senha: $password")
                println("Apartamentos: $apartments")
                println("Áreas reserváveis: $areas")
            },
            modifier = Modifier.fillMaxWidth().withPlaceholder(loading, cornerRadius = 8.dp)
                .fillMaxWidth(),
        ) {
            Text("Salvar Condomínio")
        }
    }
//    }
}
package br.com.soft.presentation.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import shared.design.component.AppText
import shared.presentation.viewmodel.provideViewModel

@Composable
fun RegisterScreen() {
    val viewModel: RegisterViewModel = provideViewModel()
    Box(Modifier.fillMaxSize()) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = viewModel::class.simpleName.orEmpty()
        )

        // Estados para armazenar os dados
        var condominiumName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var apartments by remember { mutableStateOf(listOf<String>()) }
        var newApartment by remember { mutableStateOf("") }
        var areas by remember { mutableStateOf(listOf<String>()) }
        var newArea by remember { mutableStateOf("") }

        // Layout da tela
        Column(modifier = Modifier.padding(16.dp)) {
            // Campo Nome do Condomínio
            OutlinedTextField(
                value = condominiumName,
                onValueChange = { condominiumName = it },
                label = { Text("Nome do Condomínio") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Senha
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation() // Esconde a senha
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para adicionar apartamentos
            TextField(
                value = newApartment,
                onValueChange = { newApartment = it },
                label = { Text("Adicionar Apartamento") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (newApartment.isNotEmpty()) {
                        apartments = apartments + newApartment
                        newApartment = ""
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Adicionar Apartamento")
            }

            // Listagem de apartamentos adicionados
            LazyColumn {
                items(apartments) { apartment ->
                    Text(apartment)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para adicionar áreas
            TextField(
                value = newArea,
                onValueChange = { newArea = it },
                label = { Text("Adicionar Área Reservável") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (newArea.isNotEmpty()) {
                        areas = areas + newArea
                        newArea = ""
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Adicionar Área Reservável")
            }

            // Listagem de áreas adicionadas
            LazyColumn {
                items(areas) { area ->
                    Text(area)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de salvar
            Button(
                onClick = {
                    // Lógica de salvar o condomínio, apartamentos e áreas
                    println("Condomínio: $condominiumName")
                    println("Senha: $password")
                    println("Apartamentos: $apartments")
                    println("Áreas reserváveis: $areas")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Condomínio")
            }
        }
    }
}
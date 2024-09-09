package br.com.soft.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import shared.presentation.viewmodel.provideViewModel


@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }
    var isEmailSend by remember { mutableStateOf(false) }
    val viewModel: LoginViewModel = provideViewModel()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Entrar",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.onRequestCode()
                isEmailSend = true
                // logica pra solicitar codigo
                println("Email: $email")
                println("Codigo: $codigo")
            },
            modifier = Modifier.fillMaxWidth()
                .fillMaxWidth(),
        ) {
            Text("Solicitar codigo")
        }

        if (isEmailSend) {
            Text(
                text = "E-mail enviado! Use o código para entrar.",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it },
                label = { Text("Codigo") },
                modifier = Modifier.fillMaxWidth()
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isEmailSend) {
            Button(
                onClick = {
                    viewModel.onLogin()
                    // logica pra confirmar
                    println("Email: $email")
                    println("Codigo: $codigo")
                },
                modifier = Modifier.fillMaxWidth()
                    .fillMaxWidth(),
            ) {
                Text("Entrar")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.onGoToRegister()
            },
            modifier = Modifier.fillMaxWidth()
                .fillMaxWidth(),
        ) {
            Text("Cadastre-se")
        }

    }
}

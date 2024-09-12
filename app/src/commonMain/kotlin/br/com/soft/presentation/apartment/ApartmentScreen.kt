package br.com.soft.presentation.apartment

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.soft.data.model.Apartment
import shared.presentation.viewmodel.provideViewModel


@Composable
fun ApartmentScreen(data: ApartmentDestination.Data) {
    val viewModel: ApartmentViewModel = provideViewModel()
    val apartment = data.apartment?: Apartment("", "", "", "", "")

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data.apartment?.name ?: "",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Informaçoes do responsavel",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = apartment.documento,
            onValueChange = { apartment.documento = it },
            label = { Text("Documento") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = apartment.email,
            onValueChange = { apartment.email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = apartment.numeroContato,
            onValueChange = { apartment.numeroContato = it },
            label = { Text("Número de Contato") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = apartment.observacoes,
            onValueChange = { apartment.observacoes = it },
            label = { Text("Observações") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.onSave()
            },
            modifier = Modifier.fillMaxWidth()
                .fillMaxWidth(),
        ) {
            Text("Salvar")
        }
    }
}

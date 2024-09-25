package br.com.soft.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastMap
import br.com.soft.presentation.design.AccordionCard
import br.com.soft.presentation.design.ListCard
import shared.presentation.viewmodel.provideViewModel


@Composable
fun DashBoardScreen() {
    val viewModel: DashBoardViewModel = provideViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Nome do condomínio",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AccordionCard("Apartamentos") {
            ListCard(
                list = uiState.apartmentList.fastMap { it.name },
                onClick = { index ->
                    viewModel.onGoToApartment(uiState.apartmentList[index])
                }
            )
            cardNewItem(
                uiState.newApartmentName,
                { viewModel.onApartmentNameChange(it) },
                { viewModel.onNewApartment(uiState.newApartmentName) },
                "Novo Apartamento"
            )

        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    AccordionCard("Áreas Reserváveis") {
        ListCard(
            list = uiState.sharedSpacesList.fastMap { it.name },
            onClick = { index ->
                viewModel.onGoToSharedSpaces(uiState.sharedSpacesList[index])
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        cardNewItem(
            uiState.newSharedSpacesName,
            { viewModel.onSharedSpaceNameChange(it) },
            { viewModel.onNewSharedSpacesName(uiState.newSharedSpacesName) },
            "Nova Area reservavel"
        )
    }

    Spacer(modifier = Modifier.height(100.dp))
}

@Composable
fun cardNewItem(
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    label: String
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(Modifier.fillMaxWidth().padding(16.dp)) {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {
                        onClick()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = label
                        )
                    }
                }
            )
        }
    }
}
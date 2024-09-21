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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastMap
import br.com.soft.data.model.Apartment
import br.com.soft.presentation.design.AccordionCard
import br.com.soft.presentation.design.ListCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import shared.presentation.viewmodel.provideViewModel


@Composable
fun DashBoardScreen() {
    val viewModel: DashBoardViewModel = provideViewModel()
    var name by remember {
        mutableStateOf("")
    }

    var apartmentList by remember { mutableStateOf<List<Apartment>>(emptyList()) }
    LaunchedEffect(Unit) {
        viewModel.repository.getApartments().collect { apartments ->
            apartmentList = apartments
        }
    }
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Nome do condominio",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AccordionCard("Apartamentos") {
            ListCard(
                list =  apartmentList.fastMap { l -> l.name },
                onClick = { index ->
                    viewModel.apartment.set(apartmentList[index])
                    viewModel.onGoToApartment()
                })

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Box(Modifier.fillMaxWidth().padding(16.dp)) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Novo Apartamento") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = {
                                val newApartment = Apartment(0, name, "", "", "", "")
                               viewModel.onNewApartment(newApartment)
                                apartmentList += newApartment
                                name = ""

                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Novo Apartamento"
                                )
                            }
                        }

                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        AccordionCard("Areas Reservaveis") {
            ListCard(
                list = apartmentList.fastMap { ar -> ar.name },
                onClick = { index ->
                    viewModel.apartment.set(apartmentList[index])
                    viewModel.onGoToSharedSpaces()
                })
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

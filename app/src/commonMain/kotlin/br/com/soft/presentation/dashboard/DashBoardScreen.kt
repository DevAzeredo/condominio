package br.com.soft.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastMap
import br.com.soft.data.model.Apartment
import br.com.soft.presentation.design.AccordionCard
import br.com.soft.presentation.design.ListCard
import shared.presentation.viewmodel.provideViewModel


@Composable
fun DashBoardScreen() {
    val viewModel: DashBoardViewModel = provideViewModel()
    val apartmentList = listOf(
        Apartment(
            "Apartamento A",
            "123456789",
            "john.c.calhoun@examplepetstore.com",
            "Observações teste",
            "123456789"
        ),
        Apartment(
            "Apartamento B",
            "123456789",
            "john.c.calhoun@examplepetstore.com",
            "Observações teste",
            "123456789"
        ),
        Apartment(
            "Apartamento C",
            "123456789",
            "john.c.calhoun@examplepetstore.com",
            "Observações teste",
            "123456789"
        ),
        Apartment(
            "Apartamento D",
            "123456789",
            "john.c.calhoun@examplepetstore.com",
            "Observações teste",
            "123456789"
        ),
        Apartment(
            "Apartamento E",
            "123456789",
            "john.c.calhoun@examplepetstore.com",
            "Observações teste",
            "123456789"
        )
    )

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
                apartments = apartmentList.fastMap { apartment -> apartment.name },
                onClick = {  index ->
                    viewModel.apartment.set(apartmentList[index])
                    viewModel.onGoToApartment()
                })
        }
        Spacer(modifier = Modifier.height(16.dp))
        AccordionCard("Areas Reservaveis") {
            ListCard(
                apartments = apartmentList.fastMap { apartment -> apartment.name },
                onClick = {  index ->
                    viewModel.apartment.set(apartmentList[index])
                    viewModel.onGoToApartment()
                })
        }
    }
}

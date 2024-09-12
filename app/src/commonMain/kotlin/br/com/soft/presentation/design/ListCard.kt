package br.com.soft.presentation.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListCard(apartments: List<String>, onClick: (Int) -> Unit) {
    Column {
        apartments.forEachIndexed { index, apartment ->
            ElevatedCard(
                onClick = { onClick( index) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(text = apartment, Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
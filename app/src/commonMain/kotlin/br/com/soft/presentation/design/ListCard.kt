package br.com.soft.presentation.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListCard(list: List<String>, onClick: (Int) -> Unit, icon: @Composable (Int) -> Unit) {
    Column {
        list.forEachIndexed { index, l ->
            ElevatedCard(
                onClick = { onClick(index) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = l, Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    icon(index)
                }
            }
        }
    }
}
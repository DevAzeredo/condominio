import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.soft.data.model.Event
import br.com.soft.presentation.sharedspaces.SharedSpacesDestination
import br.com.soft.presentation.sharedspaces.SharedSpacesViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import shared.design.component.TimePickerDialog
import shared.design.component.DatePickerDialog
import shared.design.icon.Timer
import shared.presentation.viewmodel.provideViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedSpacesScreen(data: SharedSpacesDestination.Data) {
    // TODO alterar para uiState
    val viewModel: SharedSpacesViewModel = provideViewModel()
    var textDate by rememberSaveable { mutableStateOf("") }
    var textTime by rememberSaveable { mutableStateOf("") }
    val openDialogData = remember { mutableStateOf(false) }
    val openDialogTime = remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState(
        initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds()
    )
    var timeState = rememberTimePickerState(is24Hour = false)
    var timeStateLog = rememberTimePickerState(is24Hour = false)

    val events = listOf(
        Event("Evento 1", "2024-09-18", "10:00"),
        Event("Evento 2", "2024-09-18", "14:00")
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data.sharedSpace?.name ?: "",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        DatePickerDialog(dateState, openDialogData)

        textDate = dateState.selectedDateMillis?.let {
            Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.UTC).date.toString()
        } ?: {
            Clock.System.now().toLocalDateTime(TimeZone.UTC).date
        }.toString()

        OutlinedTextField(
            value = textDate,
            onValueChange = { textDate = it },
            label = { Text("Data") },
            trailingIcon = {
                IconButton(onClick = {
                    openDialogData.value = !openDialogData.value
                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Selecionar Data"
                    )
                }
            },
            readOnly = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = textTime,
            onValueChange = { textTime = it },
            label = { Text("Horario") },
            trailingIcon = {
                IconButton(onClick = {
                    openDialogTime.value = !openDialogTime.value
                }) {
                    Icon(
                        imageVector = Timer,
                        contentDescription = "Selecionar horario"
                    )
                }
            },
            readOnly = true
        )
        if (openDialogTime.value) {
            TimePickerDialog(
                content = { TimeInput(timeState, modifier = Modifier.padding(8.dp)) },
                onCancel = { timeState = timeStateLog
                    openDialogTime.value = !openDialogTime.value },
                onConfirm = { timeStateLog = timeState
                    openDialogTime.value = !openDialogTime.value},
                toggle = { /* deixar vazio por enquanto, ###analisar */
                })

        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Eventos Agendados:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        events.forEach { event ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = event.name,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.onSave()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Salvar")
        }
    }
}

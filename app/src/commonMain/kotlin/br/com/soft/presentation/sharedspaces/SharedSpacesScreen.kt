@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.soft.data.model.Event
import br.com.soft.data.model.SharedSpace
import br.com.soft.presentation.sharedspaces.SharedSpacesViewModel
import br.com.soft.presentation.utils.formatDate
import br.com.soft.presentation.utils.formatHour
import br.com.soft.presentation.utils.formatMinute
import shared.design.component.AppAlertDialog
import shared.design.component.DatePickerDialog
import shared.design.component.TimePickerDialog
import shared.design.icon.Timer
import shared.presentation.viewmodel.provideViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedSpacesScreen(sharedSpace: SharedSpace) {
    val viewModel: SharedSpacesViewModel = provideViewModel()
    LaunchedEffect(key1 = sharedSpace) {
        viewModel.setSharedSpaceAndLoadEvents(sharedSpace)
    }
    val uiState by viewModel.uiState.collectAsState()
    val openDialogData = remember { mutableStateOf(false) }
    val openDialogTime = remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState(initialSelectedDateMillis = uiState.date)
    val timeState = rememberTimePickerState(is24Hour = true)

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SharedSpaceTitle(uiState.sharedSpace.name)

        Spacer(modifier = Modifier.height(16.dp))

        DateInput(uiState.date.formatDate(), openDialogData, dateState) { newDate ->
            viewModel.setDate(newDate)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TimeInput(
            "${uiState.hour.formatHour()}:${uiState.minute.formatMinute()}",
            openDialogTime,
            timeState
        ) { newTime ->
            val (hour, minute) = newTime.split(":").map { it.toInt() }
            viewModel.setHour(hour)
            viewModel.setMinute(minute)
        }

        EventsSection(
            uiState.eventList,
            { viewModel.addEvent() },
            uiState.newEventName,
            { viewModel.setNewNameEvent(it) },
            { viewModel.removeEvent(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SaveButton { viewModel.onSave() }
    }
}

@Composable
fun SharedSpaceTitle(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    dateText: String,
    openDialogData: MutableState<Boolean>,
    dateState: DatePickerState,
    onDateChange: (Long) -> Unit
) {
    DatePickerDialog(dateState, openDialogData, onDateChange)
    OutlinedTextField(
        value = dateText,
        onValueChange = { /*read only*/ },
        label = { Text("Data") },
        trailingIcon = {
            IconButton(onClick = { openDialogData.value = !openDialogData.value }) {
                Icon(
                    imageVector = Icons.Default.DateRange, contentDescription = "Selecionar Data"
                )
            }
        },
        readOnly = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInput(
    timeText: String,
    openDialogTime: MutableState<Boolean>,
    timeState: TimePickerState,
    onTimeChange: (String) -> Unit
) {
    OutlinedTextField(
        value = timeText,
        onValueChange = { /*readonly*/ },
        label = { Text("Horario") },
        trailingIcon = {
            IconButton(onClick = { openDialogTime.value = !openDialogTime.value }) {
                Icon(
                    imageVector = Timer, contentDescription = "Selecionar horario"
                )
            }
        },
        readOnly = true
    )
    if (openDialogTime.value) {
        TimePickerDialog(content = { TimeInput(timeState, modifier = Modifier.padding(8.dp)) },
            onCancel = { openDialogTime.value = false },
            onConfirm = {
                onTimeChange("${timeState.hour.formatHour()}:${timeState.minute.formatMinute()}")
                openDialogTime.value = false
            })
    }
}

@Composable
fun EventsSection(
    eventList: List<Event>,
    onAddEvent: () -> Unit,
    nameEventField: String,
    onSetNewEventName: (String) -> Unit,
    onRemove: (Event) -> Unit = {}
) {
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
        value = nameEventField,
        onValueChange = { onSetNewEventName(it) },
        label = { Text("Nome do evento") },
        readOnly = false
    )
    Spacer(modifier = Modifier.height(8.dp))
    Button(onClick = { onAddEvent() }, content = { Text("Adicionar evento") })
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Eventos Agendados:",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    EventList(eventList, onRemove)
}

@Composable
fun EventList(eventList: List<Event>, onRemove: (Event) -> Unit) {
    eventList.forEach { event ->
        EventCard(event, onRemove)
    }
}

@Composable
fun EventCard(event: Event, onRemove: (Event) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.name,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = event.date.formatDate() + " " + event.hour.formatHour() + ":" + event.minute.formatMinute(),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    Icons.Filled.Clear, contentDescription = "Remover ${event.name}"
                )
                if (showDialog) {
                    AppAlertDialog(
                        title = "Remover ${event.name}",
                        confirmLabel = "Remover",
                        text = "Deseja remover o espaco compartilhado:${event.name}",
                        confirmAction = {
                            onRemove(event)
                            showDialog = false
                        },
                        dismissLabel = "Cancelar",
                        onDismissRequest = { showDialog = false },
                        dismissAction = { showDialog = false },

                        )
                }
            }
        }
    }
}

@Composable
fun SaveButton(onSave: () -> Unit) {
    Button(
        onClick = onSave, modifier = Modifier.fillMaxWidth()
    ) {
        Text("Salvar")
    }
}
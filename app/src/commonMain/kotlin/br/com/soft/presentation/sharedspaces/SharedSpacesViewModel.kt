package br.com.soft.presentation.sharedspaces

import br.com.soft.data.model.Event
import br.com.soft.data.model.SharedSpace
import br.com.soft.data.repository.event.EventRepository
import br.com.soft.presentation.app.AppStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import shared.presentation.navigation.NavigationStore
import shared.presentation.viewmodel.BaseViewModel

class SharedSpacesViewModel(
    private val navigationStore: NavigationStore,
    private val appStore: AppStore,
    private val eventRepository: EventRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(SharedSpacesUiState())
    val uiState: StateFlow<SharedSpacesUiState> get() = _uiState.asStateFlow()

    fun onSave() {
        launchAsync("onSave", appStore) {
            eventRepository.saveChanges(_uiState.value.sharedSpace.id)
        }
    }

    fun addEvent() {
        launchAsync("addEvent") {
            if (_uiState.value.newEventName.isNotBlank()) {
                eventRepository.addEvent(
                    Event(
                        _uiState.value.eventList.size + 1,
                        _uiState.value.newEventName,
                        _uiState.value.date,
                        _uiState.value.hour,
                        _uiState.value.minute
                    )
                )
                setNewNameEvent("")
            }
        }
    }

    fun setSharedSpaceAndLoadEvents(sharedSpace: SharedSpace) {
        launchAsync("setSharedSpace") {
            _uiState.value = _uiState.value.copy(
                sharedSpace = sharedSpace
            )
            fetchEvents()
        }
    }

    private suspend fun fetchEvents() {
        eventRepository.setSharedSpaceId(_uiState.value.sharedSpace.id)
        eventRepository.getEvents().collect { events ->
            _uiState.value = _uiState.value.copy(eventList = events)
        }
    }

    fun setHour(hour: Int) {
        _uiState.value = _uiState.value.copy(
            hour = hour
        )
    }

    fun setDate(date: Long) {
        _uiState.value = _uiState.value.copy(
            date = date
        )
    }

    fun setMinute(minute: Int) {
        _uiState.value = _uiState.value.copy(
            minute = minute
        )
    }

    fun setNewNameEvent(newEventName: String) {
        _uiState.value = _uiState.value.copy(
            newEventName = newEventName
        )
    }

    fun removeEvent(event: Event) {
        launchAsync("removeEvent") {
            eventRepository.removeEvent(event)
        }
    }

    fun onBack() = navigationStore.onBack()

    data class SharedSpacesUiState(
        val name: String = "",
        val eventList: List<Event> = emptyList(),
        val date: Long = Clock.System.now().toEpochMilliseconds(),
        val hour: Int = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).hour,
        val minute: Int = 0,
        val errorMessage: String? = null,
        val sharedSpace: SharedSpace = SharedSpace(0, "", "", "", "", ""),
        val newEventName: String = ""
    )
}
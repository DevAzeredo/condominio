package br.com.soft.presentation.sharedspaces

import br.com.soft.data.model.Event
import br.com.soft.data.model.SharedSpace
import br.com.soft.presentation.app.AppStore
import kotlinx.coroutines.delay
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
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(SharedSpacesUiState())
    val uiState: StateFlow<SharedSpacesUiState> get() = _uiState.asStateFlow()

    fun onSave() {
        launchAsync("onRequestCode", appStore) {
            // logica p salvar
            delay(3000L)
        }
    }

    fun setSharedSpace(sharedSpace: SharedSpace) {
        launchAsync("setSharedSpace") {
            _uiState.value = _uiState.value.copy(
                sharedSpace = sharedSpace
            )
        }
    }

    fun setHour(hour: Int) {
        launchAsync("setHour") {
            _uiState.value = _uiState.value.copy(
                hour = hour
            )
        }
    }

    fun setDate(date: Long) {
        launchAsync("setDate") {
            _uiState.value = _uiState.value.copy(
                date = date
            )
        }
    }

    fun setMinute(minute: Int) {
        launchAsync("setMinute") {
            _uiState.value = _uiState.value.copy(
                minute = minute
            )
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
        val sharedSpace: SharedSpace = SharedSpace(0, "", "", "", "", "")
    )
}
package br.com.soft.presentation.dashboard

import br.com.soft.data.model.Apartment
import br.com.soft.data.model.SharedSpace
import br.com.soft.data.repository.apartment.ApartmentRepository
import br.com.soft.data.repository.sharedSpaces.SharedSpaceRepository
import br.com.soft.presentation.apartment.ApartmentDestination
import br.com.soft.presentation.app.AppStore
import br.com.soft.presentation.sharedspaces.SharedSpacesDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import shared.presentation.navigation.NavigationStore
import shared.presentation.viewmodel.BaseViewModel

class DashBoardViewModel(
    private val navigationStore: NavigationStore,
    private val appStore: AppStore,
    private val apartmentRepository: ApartmentRepository,
    private val sharedSpacesRepository: SharedSpaceRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> get() = _uiState.asStateFlow()

    init {
        fetchApartments()
        fetchSharedSpaces()
    }

    fun onGoToApartment(apartment: Apartment) {
        launchAsync("onGoToApartment", appStore) {
            navigationStore.onNext(
                destination = ApartmentDestination,
                data = ApartmentDestination.Data(
                    apartment = apartment
                )
            )
        }
    }

    fun onGoToSharedSpaces(sharedSpace: SharedSpace) {
        launchAsync("onGoToSharedSpaces", appStore) {
            navigationStore.onNext(
                destination = SharedSpacesDestination,
                data = SharedSpacesDestination.Data(
                    sharedSpace = sharedSpace
                )
            )
        }
    }

    private fun fetchSharedSpaces() {
        launchAsync("fetchSharedSpaces") {
            sharedSpacesRepository.getSharedSpace().collect { sharedSpacesList ->
                _uiState.value = _uiState.value.copy(
                    sharedSpacesList = sharedSpacesList
                )
            }
        }
    }

    private fun fetchApartments() {
        launchAsync("fetchApartments") {
            apartmentRepository.getApartments().collect { apartmentsList ->
                _uiState.value = _uiState.value.copy(
                    apartmentList = apartmentsList
                )
            }
        }
    }

    fun onNewApartment(newApartmentName: String) {
        if (newApartmentName.trim().isEmpty()) return
        val newApartment = Apartment(0, newApartmentName, "", "", "", "")
        launchAsync("onNewApartment", appStore) {
            apartmentRepository.addApartment(newApartment)
            apartmentRepository.saveChanges()
            fetchApartments()
            _uiState.value = _uiState.value.copy(newApartmentName = "")
        }
    }

    fun onRemoveApartment(removedApartment: Apartment) {
        launchAsync("onRemoveApartment", appStore) {
            apartmentRepository.removeApartment(removedApartment)
            apartmentRepository.saveChanges()
            fetchApartments()
        }
    }

    fun onRemoveSharedSpaces(removedSharedSpace: SharedSpace) {
        launchAsync("onRemoveSharedSpace", appStore) {
            sharedSpacesRepository.removeSharedSpace(removedSharedSpace)
            sharedSpacesRepository.saveChanges()
            fetchSharedSpaces()
        }
    }

    fun onNewSharedSpacesName(newSharedSpacesName: String) {
        if (newSharedSpacesName.trim().isEmpty()) return
        val newSharedSpace = SharedSpace(0, newSharedSpacesName, "", "", "", "")
        launchAsync("onNewSharedSpacesName", appStore) {
            sharedSpacesRepository.addSharedSpace(newSharedSpace)
            sharedSpacesRepository.saveChanges()
            fetchApartments()
            _uiState.value = _uiState.value.copy(newSharedSpacesName = "")
        }
    }


    fun onApartmentNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(newApartmentName = newName)
    }

    fun onSharedSpaceNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(newSharedSpacesName = newName)
    }

    data class DashboardUiState(
        val apartmentList: List<Apartment> = emptyList(),
        val sharedSpacesList: List<SharedSpace> = emptyList(),
        val newApartmentName: String = "",
        val newSharedSpacesName: String = "",
        val errorMessage: String? = null,
    )
}
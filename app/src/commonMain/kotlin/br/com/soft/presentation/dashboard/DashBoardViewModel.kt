package br.com.soft.presentation.dashboard

import br.com.soft.data.model.Apartment
import br.com.soft.data.repository.Apartment.ApartmentRepository
import br.com.soft.presentation.apartment.ApartmentDestination
import br.com.soft.presentation.app.AppStore
import br.com.soft.presentation.sharedspaces.SharedSpacesDestination
import shared.presentation.navigation.NavigationStore
import shared.presentation.store.DataState
import shared.presentation.viewmodel.BaseViewModel

class DashBoardViewModel(
    private val navigationStore: NavigationStore,
    private val appStore: AppStore,
    val repository: ApartmentRepository
) : BaseViewModel() {

    var apartment = DataState<Apartment>()

    fun onGoToApartment() {
        launchAsync("onGoToApartment", appStore) {
            navigationStore.onNext(
                destination = ApartmentDestination,
                data = ApartmentDestination.Data(
                    apartment = apartment.get()
                )
            )
        }
    }

    fun onNewApartment(newApartment: Apartment) {
        launchAsync("onNewApartment", appStore) {
            repository.addApartment(newApartment)
            repository.saveChanges()
            repository.getApartments()
        }
    }

    fun onGoToSharedSpaces() {
        launchAsync("onGoToSharedSpaces", appStore) {
            navigationStore.onNext(
                destination = SharedSpacesDestination,
                data = SharedSpacesDestination.Data(
                    apartment = apartment.get()
                )
            )
        }
    }


    fun onBack() = navigationStore.onBack()

}
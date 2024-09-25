package br.com.soft.presentation.apartment

import br.com.soft.data.model.Apartment
import br.com.soft.data.repository.apartment.ApartmentRepository
import br.com.soft.presentation.app.AppStore
import kotlinx.coroutines.flow.count
import shared.presentation.navigation.NavigationStore
import shared.presentation.viewmodel.BaseViewModel

class ApartmentViewModel(
    private val navigationStore: NavigationStore,
    private val appStore: AppStore,
    private val repository: ApartmentRepository
) : BaseViewModel() {
    lateinit var apartment: Apartment

    fun onSave() {
        launchAsync("onSave", appStore) {
            if (apartment.id == 0) {
                apartment.id = repository.getApartments().count() + 1
                repository.addApartment(apartment)
            }
            repository.saveChanges()
        }
    }

    fun onBack() = navigationStore.onBack()


}
package br.com.soft.presentation.apartment

import br.com.soft.presentation.app.AppStore
import br.com.soft.presentation.register.RegisterDestination
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import shared.data.source.keyvalue.KeyValueSource
import shared.presentation.navigation.NavigationStore
import shared.presentation.viewmodel.BaseViewModel

class ApartmentViewModel(
    private val navigationStore: NavigationStore,
    private val appStore: AppStore,
    private val keyValueSource: KeyValueSource
) : BaseViewModel() {


    fun onSave() {
        launchAsync("onRequestCode", appStore) {
            // logica p salvar
            delay(3000L)
        }
    }

    fun onBack() = navigationStore.onBack()


}
package br.com.soft.presentation.sharedspaces

import br.com.soft.presentation.app.AppStore
import kotlinx.coroutines.delay
import shared.data.source.keyvalue.KeyValueSource
import shared.presentation.navigation.NavigationStore
import shared.presentation.viewmodel.BaseViewModel

class SharedSpacesViewModel(
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
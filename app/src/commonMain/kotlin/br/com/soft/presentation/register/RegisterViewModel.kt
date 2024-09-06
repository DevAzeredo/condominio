package br.com.soft.presentation.register

import br.com.soft.presentation.app.AppStore
import kotlinx.coroutines.delay
import shared.presentation.navigation.NavigationStore
import shared.presentation.store.DataState
import shared.presentation.viewmodel.BaseViewModel

class RegisterViewModel(
    private val navigationStore: NavigationStore,
    private val appStore: AppStore
) : BaseViewModel() {
    val loadingState = DataState(false)

    fun onShow() {
        launchAsync("onShow") {
            loadingState.set(true)
            delay(3000L)
            loadingState.set(false)
        }
    }

    fun onBack() = navigationStore.onBack()

}
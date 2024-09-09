package br.com.soft.presentation.login

import br.com.soft.presentation.app.AppStore
import br.com.soft.presentation.register.RegisterDestination
import kotlinx.coroutines.delay
import shared.presentation.navigation.NavigationStore
import shared.presentation.store.DataState
import shared.presentation.viewmodel.BaseViewModel

class LoginViewModel(
    private val navigationStore: NavigationStore,
    private val appStore: AppStore
) : BaseViewModel() {

    fun onGoToRegister() {
        launchAsync("onGoToCadastrar", appStore) {
            navigationStore.onNext(RegisterDestination)
        }
    }

    fun onLogin() {
        launchAsync("onLogin", appStore) {
            delay(3000L)
        }
    }
    fun onRequestCode() {
        launchAsync("onRequestCode", appStore) {
            // fazer logica pra enviar codigo
            delay(3000L)
        }
    }


    fun onBack() = navigationStore.onBack()

}
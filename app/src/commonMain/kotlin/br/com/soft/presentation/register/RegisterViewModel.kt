package br.com.soft.presentation.register

import shared.presentation.navigation.NavigationStore
import shared.presentation.viewmodel.BaseViewModel

class RegisterViewModel(
    private val navigationStore: NavigationStore
) : BaseViewModel() {

    fun onBack() = navigationStore.onBack()

}
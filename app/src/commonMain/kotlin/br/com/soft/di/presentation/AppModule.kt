package br.com.soft.di.presentation

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.soft.presentation.apartment.ApartmentViewModel
import br.com.soft.presentation.app.AppNavigationRouter
import br.com.soft.presentation.app.AppStore
import br.com.soft.presentation.app.AppViewModel
import br.com.soft.presentation.dashboard.DashBoardViewModel
import br.com.soft.presentation.loader.LoaderViewModel
import br.com.soft.presentation.login.LoginViewModel
import br.com.soft.presentation.navigation.NavigationBarViewModel
import br.com.soft.presentation.register.RegisterViewModel
import br.com.soft.presentation.sharedspaces.SharedSpacesViewModel
import br.com.soft.presentation.theme.AppThemeViewModel
import org.koin.dsl.module
import shared.design.component.AppSnackbarStore

val appModule = module {
    single { AppStore() }
    single { AppSnackbarStore() }
    single { AppNavigationRouter() }
    single {
        viewModelFactory {
            initializer { AppViewModel(get(), get(), get(), get()) }
            initializer { AppThemeViewModel(get()) }
            initializer { NavigationBarViewModel(get(), get()) }
            initializer { LoaderViewModel(get()) }
            initializer { RegisterViewModel(get(), get()) }
            initializer { LoginViewModel(get(), get()) }
            initializer { DashBoardViewModel(get(), get(), get(), get()) }
            initializer { ApartmentViewModel(get(), get(), get()) }
            initializer { SharedSpacesViewModel(get(), get(), get()) }
        }
    }
}
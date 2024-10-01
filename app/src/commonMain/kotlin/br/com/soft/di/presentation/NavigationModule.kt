package br.com.soft.di.presentation

import br.com.soft.presentation.apartment.ApartmentDestination
import br.com.soft.presentation.dashboard.DashBoardDestination
import br.com.soft.presentation.login.LoginDestination
import br.com.soft.presentation.register.RegisterDestination
import br.com.soft.presentation.sharedspaces.SharedSpacesDestination
import org.koin.dsl.module
import shared.presentation.navigation.NavigationStore

val navigationModule = module {
    single {
        NavigationStore(
            destinations = listOf(
                RegisterDestination,
                LoginDestination,
                DashBoardDestination,
                ApartmentDestination,
                SharedSpacesDestination
            )
        )
    }
}
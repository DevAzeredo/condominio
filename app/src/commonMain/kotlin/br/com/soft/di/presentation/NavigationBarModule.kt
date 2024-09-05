package br.com.soft.di.presentation

import androidx.compose.runtime.Composable
import br.com.soft.presentation.navigation.NavigationBarPage
import br.com.soft.presentation.navigation.NavigationBarStore
import br.com.soft.presentation.navigation.samples.a.NavigationADestination
import br.com.soft.presentation.navigation.samples.b.NavigationBDestination
import br.com.soft.presentation.navigation.samples.c.NavigationCDestination
import br.com.soft.presentation.register.RegisterDestination
import br.com.soft.presentation.showcases.ShowcasesDestination
import org.koin.dsl.module
import shared.presentation.navigation.NavigationDestination
import shared.presentation.navigation.NavigationStore
import shared.presentation.navigation.NavigationStrategy
import shared.design.icon.AppIconModel
import shared.design.icon.AppIcons

val navigationBarModule = module {
    single {
        NavigationBarStore(
            pages = listOf(
                createPage(
                    store = get(),
                    destination = ShowcasesDestination,
                    getActiveIcon = { AppIcons.school },
                    getInactiveIcon = { AppIcons.school },
                    getLabel = { "Showcases" }
                ),
                createPage(
                    store = get(),
                    destination = RegisterDestination,
                    getActiveIcon = { AppIcons.wineBar },
                    getInactiveIcon = { AppIcons.wineBar },
                    getLabel = { "Page Register" }
                ),
                createPage(
                    store = get(),
                    destination = NavigationBDestination,
                    getActiveIcon = { AppIcons.localDrink },
                    getInactiveIcon = { AppIcons.localDrink },
                    getLabel = { "Page 2" }
                ),
                createPage(
                    store = get(),
                    destination = NavigationCDestination,
                    getActiveIcon = { AppIcons.coffee },
                    getInactiveIcon = { AppIcons.coffee },
                    getLabel = { "Page 3" }
                )
            ),
            allowedDestinations = setOf(
            ),
            restrictedDestinations = setOf(
            )
        )
    }
}

private fun <D> createPage(
    store: NavigationStore,
    destination: NavigationDestination<D>,
    getInactiveIcon: () -> AppIconModel,
    getActiveIcon: () -> AppIconModel,
    getLabel: @Composable () -> String?,
): NavigationBarPage {
    return NavigationBarPage(
        enabled = true,
        id = destination.id,
        getLabel = getLabel,
        alwaysShowLabel = false,
        getActiveIcon = getActiveIcon,
        getInactiveIcon = getInactiveIcon,
        onClick = { navigate(store, destination) }
    )
}

private fun <D> navigate(
    store: NavigationStore,
    destination: NavigationDestination<D>
) {
    store.onNext(
        destination = destination,
        strategy = NavigationStrategy.SingleInstance
    )
}
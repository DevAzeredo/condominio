package br.com.soft.presentation.app

import androidx.compose.runtime.Composable
import br.com.soft.presentation.loader.LoaderProvider
import br.com.soft.presentation.navigation.NavigationBarProvider
import shared.design.container.AppScaffold
import shared.presentation.navigation.rememberNavigationContext
import shared.presentation.viewmodel.provideViewModel

/**
 * The main screen of the application.
 */
@Composable
fun AppScreen() {
    val viewModel: AppViewModel = provideViewModel()
    val navigationState = viewModel.navigationStore
    val navigationContext = rememberNavigationContext(navigationState)
    NavigationBarProvider {
        AppScaffold(
            snackbarState = viewModel.snackbarStore,
            navigationContext = navigationContext,
        )
    }
    LoaderProvider(viewModel.appStore)
}
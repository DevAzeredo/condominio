package br.com.soft.presentation.app

import br.com.soft.presentation.showcases.ShowcasesDestination
import shared.presentation.navigation.NavigationDestination

/**
 * Application navigation router.
 */
class AppNavigationRouter {

    /**
     * Returns the start destination based on the current application state.
     *
     * @return The start destination.
     */
    suspend fun getStartDestination(): NavigationDestination<*> {
        return ShowcasesDestination
    }

}
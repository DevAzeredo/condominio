package br.com.soft.presentation.login

import androidx.navigation.NavGraphBuilder
import shared.presentation.navigation.NavigationDestinationNoArgs
import shared.presentation.navigation.NavigationStrategy

object LoginDestination : NavigationDestinationNoArgs() {

    override val id: String = "login_screen"
    override val navStrategy: NavigationStrategy = NavigationStrategy.NewInstance
    override fun doBind(builder: NavGraphBuilder) = composable(builder) { LoginScreen() }

}
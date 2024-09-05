package br.com.soft.presentation.register

import androidx.navigation.NavGraphBuilder
import shared.presentation.navigation.NavigationDestinationNoArgs
import shared.presentation.navigation.NavigationStrategy

object RegisterDestination : NavigationDestinationNoArgs() {

    override val id: String = "register_screen"
    override val navStrategy: NavigationStrategy = NavigationStrategy.NewInstance
    override fun doBind(builder: NavGraphBuilder) = composable(builder) { RegisterScreen() }

}
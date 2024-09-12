package br.com.soft.presentation.dashboard

import androidx.navigation.NavGraphBuilder
import shared.presentation.navigation.NavigationDestinationNoArgs
import shared.presentation.navigation.NavigationStrategy

object DashBoardDestination : NavigationDestinationNoArgs() {

    override val id: String = "dash_board_screen"
    override val navStrategy: NavigationStrategy = NavigationStrategy.NewInstance
    override fun doBind(builder: NavGraphBuilder) = composable(builder) { DashBoardScreen() }

}
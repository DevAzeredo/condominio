package br.com.soft.presentation.apartment

import androidx.navigation.NavGraphBuilder
import br.com.soft.data.model.Apartment
import br.com.soft.presentation.showcases.userflow.navigation.args.to.ArgsNavigationToDestination
import br.com.soft.presentation.showcases.userflow.navigation.args.to.ArgsNavigationToDestination.Data
import kotlinx.serialization.Serializable
import shared.presentation.navigation.ArgsStrategy
import shared.presentation.navigation.NavigationDestination
import shared.presentation.navigation.NavigationDestinationNoArgs
import shared.presentation.navigation.NavigationStrategy

object ApartmentDestination : NavigationDestination<ApartmentDestination.Data>() {

    override val id: String = "apartment_screen"
    override val argsStrategy: ArgsStrategy<Data> = ArgsStrategy.json(Data.serializer())
    override val navStrategy: NavigationStrategy = NavigationStrategy.NewInstance
    override fun doBind(builder: NavGraphBuilder) = composable(builder) { ApartmentScreen(it!!) }

    @Serializable
    data class Data(
        val apartment: Apartment?
    )
}
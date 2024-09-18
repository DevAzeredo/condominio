package br.com.soft.presentation.sharedspaces

import SharedSpacesScreen
import androidx.navigation.NavGraphBuilder
import br.com.soft.data.model.Apartment
import kotlinx.serialization.Serializable
import shared.presentation.navigation.ArgsStrategy
import shared.presentation.navigation.NavigationDestination
import shared.presentation.navigation.NavigationStrategy

object SharedSpacesDestination : NavigationDestination<SharedSpacesDestination.Data>() {

    override val id: String = "shared_spaces_screen"
    override val argsStrategy: ArgsStrategy<Data> = ArgsStrategy.json(Data.serializer())
    override val navStrategy: NavigationStrategy = NavigationStrategy.NewInstance
    override fun doBind(builder: NavGraphBuilder) = composable(builder) { SharedSpacesScreen(it!!) }

    @Serializable
    data class Data(
        val apartment: Apartment?
    )
}
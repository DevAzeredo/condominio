package br.com.soft.presentation.sharedspaces

import SharedSpacesScreen
import androidx.navigation.NavGraphBuilder
import br.com.soft.data.model.Apartment
import br.com.soft.data.model.SharedSpace
import kotlinx.serialization.Serializable
import shared.presentation.navigation.ArgsStrategy
import shared.presentation.navigation.NavigationDestination
import shared.presentation.navigation.NavigationStrategy

object SharedSpacesDestination : NavigationDestination<SharedSpace>() {

    override val id: String = "shared_spaces_screen"
    override val argsStrategy: ArgsStrategy<SharedSpace> = ArgsStrategy.json(SharedSpace.serializer())
    override val navStrategy: NavigationStrategy = NavigationStrategy.NewInstance
    override fun doBind(builder: NavGraphBuilder) = composable(builder) { SharedSpacesScreen(it!!) }


}
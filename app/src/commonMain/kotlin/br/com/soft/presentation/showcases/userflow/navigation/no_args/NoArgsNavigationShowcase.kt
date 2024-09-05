package br.com.soft.presentation.showcases.userflow.navigation.no_args

import br.com.soft.presentation.showcases.ShowcaseItem
import br.com.soft.presentation.showcases.ShowcasesViewModel
import br.com.soft.presentation.showcases.userflow.navigation.no_args.from.NoArgsNavigationFromDestination
import br.com.soft.presentation.showcases.userflow.navigation.no_args.to.NoArgsNavigationToDestination
import shared.presentation.navigation.NavigationDestination

object NoArgsNavigationShowcase : ShowcaseItem {

    override val label: String = "Navigation without arguments"

    override fun onClick(viewModel: ShowcasesViewModel) {
        viewModel.navigationStore.onNext(NoArgsNavigationFromDestination)
    }

    override fun dependsOn(): List<NavigationDestination<*>> = listOf(
        NoArgsNavigationFromDestination,
        NoArgsNavigationToDestination
    )

}
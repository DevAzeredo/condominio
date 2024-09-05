package br.com.soft.presentation.showcases.userflow.navigation.args

import br.com.soft.presentation.showcases.ShowcaseItem
import br.com.soft.presentation.showcases.ShowcasesViewModel
import br.com.soft.presentation.showcases.userflow.navigation.args.from.ArgsNavigationFromDestination
import br.com.soft.presentation.showcases.userflow.navigation.args.to.ArgsNavigationToDestination
import shared.presentation.navigation.NavigationDestination

object ArgsNavigationShowcase : ShowcaseItem {

    override val label: String = "Navigation with arguments"

    override fun onClick(viewModel: ShowcasesViewModel) {
        viewModel.navigationStore.onNext(ArgsNavigationFromDestination)
    }

    override fun dependsOn(): List<NavigationDestination<*>> = listOf(
        ArgsNavigationFromDestination,
        ArgsNavigationToDestination
    )

}
package br.com.soft.presentation.showcases.userflow.component.placeholder

import br.com.soft.presentation.showcases.ShowcaseItem
import br.com.soft.presentation.showcases.ShowcasesViewModel
import shared.presentation.navigation.NavigationDestination

object PlaceholderShowcase : ShowcaseItem {

    override val label: String = "Placeholders"

    override fun onClick(viewModel: ShowcasesViewModel) {
        viewModel.navigationStore.onNext(PlaceholderShowcaseDestination)
    }

    override fun dependsOn(): List<NavigationDestination<*>> = listOf(
        PlaceholderShowcaseDestination
    )

}
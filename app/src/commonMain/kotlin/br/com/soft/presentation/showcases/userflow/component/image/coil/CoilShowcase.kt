package br.com.soft.presentation.showcases.userflow.component.image.coil

import br.com.soft.presentation.showcases.ShowcaseItem
import br.com.soft.presentation.showcases.ShowcasesViewModel
import shared.presentation.navigation.NavigationDestination

object CoilShowcase : ShowcaseItem {

    override val label: String = "Coil Image Library"

    override fun onClick(viewModel: ShowcasesViewModel) {
        viewModel.navigationStore.onNext(CoilShowcaseDestination)
    }

    override fun dependsOn(): List<NavigationDestination<*>> = listOf(
        CoilShowcaseDestination
    )

}
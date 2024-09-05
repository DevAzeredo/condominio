package br.com.soft.presentation.showcases.dataflow.encryption

import br.com.soft.presentation.showcases.ShowcaseItem
import br.com.soft.presentation.showcases.ShowcasesViewModel
import shared.presentation.navigation.NavigationDestination

object BasicEncryptionShowcase : ShowcaseItem {

    override val label: String = "Basic Encryption Usage"

    override fun onClick(viewModel: ShowcasesViewModel) {
        viewModel.navigationStore.onNext(BasicEncryptionDestination)
    }

    override fun dependsOn(): List<NavigationDestination<*>> = listOf(
        BasicEncryptionDestination
    )

}
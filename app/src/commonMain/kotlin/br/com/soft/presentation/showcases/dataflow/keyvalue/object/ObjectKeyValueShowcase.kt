package br.com.soft.presentation.showcases.dataflow.keyvalue.`object`

import br.com.soft.presentation.showcases.ShowcaseItem
import br.com.soft.presentation.showcases.ShowcasesViewModel
import shared.presentation.navigation.NavigationDestination

object ObjectKeyValueShowcase : ShowcaseItem {

    override val label: String = "Save and restore objects"

    override fun onClick(viewModel: ShowcasesViewModel) {
        viewModel.navigationStore.onNext(ObjectKeyValueDestination)
    }

    override fun dependsOn(): List<NavigationDestination<*>> = listOf(
        ObjectKeyValueDestination
    )

}
package br.com.soft.presentation.showcases.userflow.passcode

import br.com.soft.presentation.passcode.ui.set.SetPasscodeDestination
import br.com.soft.presentation.showcases.ShowcaseItem
import br.com.soft.presentation.showcases.ShowcasesViewModel

object SetPasscodeShowcase : ShowcaseItem {

    override val label: String = "Set Passcode"

    override fun onClick(viewModel: ShowcasesViewModel) {
        viewModel.navigationStore.onNext(SetPasscodeDestination)
    }

}
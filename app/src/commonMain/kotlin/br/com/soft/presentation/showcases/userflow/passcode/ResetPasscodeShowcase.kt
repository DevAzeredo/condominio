package br.com.soft.presentation.showcases.userflow.passcode

import br.com.soft.presentation.passcode.ui.reset.ResetPasscodeDestination
import br.com.soft.presentation.showcases.ShowcaseItem
import br.com.soft.presentation.showcases.ShowcasesViewModel

object ResetPasscodeShowcase : ShowcaseItem {

    override val label: String = "Reset Passcode"

    override fun onClick(viewModel: ShowcasesViewModel) {
        viewModel.navigationStore.onNext(ResetPasscodeDestination)
    }

}
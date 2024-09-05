package br.com.soft.presentation.passcode.ui.reset

import androidx.compose.runtime.Composable
import br.com.soft.presentation.passcode.ui.common.PasscodeKeyboard
import org.jetbrains.compose.resources.stringResource
import shared.design.container.AppFixedTopBarColumn
import shared.presentation.viewmodel.provideViewModel
import condominio.app.generated.resources.Res
import condominio.app.generated.resources.passcode_reset_title

@Composable
fun ResetPasscodeScreen() {
    val viewModel: ResetPasscodeViewModel = provideViewModel()
    AppFixedTopBarColumn(
        onBack = viewModel::onBack
    ) {
        PasscodeKeyboard(
            title = stringResource(Res.string.passcode_reset_title),
            codeState = viewModel.enteredCodeState,
            codeLength = viewModel.passcodeLength,
            errorState = viewModel.errorStore,
            onCodeChange = viewModel::onReset
        )
    }
}
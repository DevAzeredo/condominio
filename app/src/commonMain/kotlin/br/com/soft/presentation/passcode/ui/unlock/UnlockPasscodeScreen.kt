package br.com.soft.presentation.passcode.ui.unlock

import androidx.compose.runtime.Composable
import br.com.soft.presentation.passcode.ui.common.PadTextButton
import br.com.soft.presentation.passcode.ui.common.PasscodeKeyboard
import br.com.soft.presentation.passcode.ui.forgot.ForgotPasscodeDialog
import org.jetbrains.compose.resources.stringResource
import shared.design.container.AppFixedTopBarColumn
import shared.presentation.viewmodel.provideViewModel
import condominio.app.generated.resources.Res
import condominio.app.generated.resources.passcode_unlock_forgot
import condominio.app.generated.resources.passcode_unlock_title

@Composable
fun UnlockPasscodeScreen() {
    val viewModel: UnlockPasscodeViewModel = provideViewModel()

    AppFixedTopBarColumn {
        PasscodeKeyboard(
            title = stringResource(Res.string.passcode_unlock_title),
            codeState = viewModel.enteredCodeState,
            codeLength = viewModel.passcodeLength,
            errorState = viewModel.errorStore,
            onCodeChange = viewModel::onUnlock,
            bottomLeftBlock = {
                PadTextButton(
                    text = stringResource(Res.string.passcode_unlock_forgot),
                    onClick = viewModel::onForgot
                )
            }
        )
    }

    ForgotPasscodeDialog(viewModel.forgotState)
}
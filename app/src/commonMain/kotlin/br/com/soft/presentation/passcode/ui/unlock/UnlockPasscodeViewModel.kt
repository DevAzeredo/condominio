package br.com.soft.presentation.passcode.ui.unlock

import br.com.soft.presentation.passcode.model.LockState
import br.com.soft.presentation.passcode.model.PasscodeStore
import br.com.soft.presentation.passcode.usecase.UnlockPasscode
import org.jetbrains.compose.resources.getString
import shared.presentation.store.DataState
import shared.presentation.viewmodel.BaseViewModel
import condominio.app.generated.resources.Res
import condominio.app.generated.resources.passcode_unlock_error

class UnlockPasscodeViewModel(
    private val unlockPasscode: UnlockPasscode,
    private val passcodeStore: PasscodeStore,
) : BaseViewModel() {

    val passcodeLength = passcodeStore.passcodeLength
    val enteredCodeState = DataState<String>()
    val forgotState = DataState<Boolean>()
    val errorStore = DataState<String>()

    override fun doBind() {
        enteredCodeState.clear()
    }

    fun onForgot() {
        forgotState.set(true)
    }

    fun onUnlock(enteredCode: String) {
        enteredCodeState.set(enteredCode)
        errorStore.clear()

        if (enteredCode.length != passcodeLength) {
            return
        }

        launchAsync("Unlock passcode", passcodeStore) {
            val lock = unlockPasscode.invoke(enteredCode)
            if (lock == LockState.LOCKED) {
                val attempts = passcodeStore.getRemainingUnlockAttempts()
                val error = getString(Res.string.passcode_unlock_error, attempts)
                enteredCodeState.clear()
                errorStore.set(error)
            }
            passcodeStore.lockState.set(lock)
        }
    }
}
package br.com.soft.di.presentation

import br.com.soft.presentation.passcode.model.PasscodeStore
import br.com.soft.presentation.passcode.usecase.ForgotPasscode
import br.com.soft.presentation.passcode.usecase.InitPasscode
import br.com.soft.presentation.passcode.usecase.IsPasscodeSet
import br.com.soft.presentation.passcode.usecase.PausePasscode
import br.com.soft.presentation.passcode.usecase.ResetPasscode
import br.com.soft.presentation.passcode.usecase.SetPasscode
import br.com.soft.presentation.passcode.usecase.UnlockPasscode
import org.koin.dsl.module

val passcodeModule = module {
    single { PasscodeStore() }

    factory { InitPasscode(get(), get()) }
    factory { ResetPasscode(get(), get()) }
    factory { IsPasscodeSet(get(), get()) }
    factory { PausePasscode(get(), get()) }
    factory { ForgotPasscode(get(), get()) }
    factory { SetPasscode(get(), get(), get()) }
    factory { UnlockPasscode(get(), get(), get()) }
}
package br.com.soft.di.presentation

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.soft.presentation.app.AppNavigationRouter
import br.com.soft.presentation.app.AppStore
import br.com.soft.presentation.app.AppViewModel
import br.com.soft.presentation.loader.LoaderViewModel
import br.com.soft.presentation.login.LoginViewModel
import br.com.soft.presentation.navigation.NavigationBarViewModel
import br.com.soft.presentation.navigation.samples.a.NavigationAViewModel
import br.com.soft.presentation.navigation.samples.b.NavigationBViewModel
import br.com.soft.presentation.navigation.samples.c.NavigationCViewModel
import br.com.soft.presentation.passcode.PasscodeViewModel
import br.com.soft.presentation.passcode.ui.forgot.ForgotPasscodeViewModel
import br.com.soft.presentation.passcode.ui.reset.ResetPasscodeViewModel
import br.com.soft.presentation.passcode.ui.set.SetPasscodeViewModel
import br.com.soft.presentation.passcode.ui.unlock.UnlockPasscodeViewModel
import br.com.soft.presentation.register.RegisterViewModel
import br.com.soft.presentation.showcases.ShowcasesViewModel
import br.com.soft.presentation.showcases.dataflow.encryption.BasicEncryptionViewModel
import br.com.soft.presentation.showcases.dataflow.keyvalue.`object`.ObjectKeyValueViewModel
import br.com.soft.presentation.showcases.dataflow.keyvalue.primitive.PrimitiveKeyValueViewModel
import br.com.soft.presentation.showcases.userflow.component.image.coil.CoilShowcaseViewModel
import br.com.soft.presentation.showcases.userflow.component.placeholder.PlaceholderShowcaseViewModel
import br.com.soft.presentation.showcases.userflow.loader.data.DataLoaderShowcaseViewModel
import br.com.soft.presentation.showcases.userflow.navigation.args.from.ArgsNavigationFromViewModel
import br.com.soft.presentation.showcases.userflow.navigation.args.to.ArgsNavigationToViewModel
import br.com.soft.presentation.showcases.userflow.navigation.no_args.from.NoArgsNavigationFromViewModel
import br.com.soft.presentation.showcases.userflow.navigation.no_args.to.NoArgsNavigationToViewModel
import br.com.soft.presentation.template.screen_with_args.TemplateViewModel
import br.com.soft.presentation.template.screen_without_args.TemplateNoArgsViewModel
import br.com.soft.presentation.theme.AppThemeViewModel
import org.koin.dsl.module
import shared.design.component.AppSnackbarStore

val appModule = module {
    single { AppStore() }
    single { AppSnackbarStore() }
    single { AppNavigationRouter() }
    single {
        viewModelFactory {
            initializer { AppViewModel(get(), get(), get(), get()) }
            initializer { AppThemeViewModel(get()) }
            initializer { TemplateNoArgsViewModel(get()) }
            initializer { TemplateViewModel(get()) }
            initializer { ShowcasesViewModel(get()) }
            initializer { NavigationAViewModel() }
            initializer { NavigationBViewModel() }
            initializer { NavigationCViewModel() }
            initializer { NavigationBarViewModel(get(), get()) }
            initializer { NoArgsNavigationFromViewModel(get()) }
            initializer { NoArgsNavigationToViewModel(get()) }
            initializer { ArgsNavigationFromViewModel(get()) }
            initializer { ArgsNavigationToViewModel(get()) }
            initializer { LoaderViewModel(get()) }
            initializer { DataLoaderShowcaseViewModel(get(), get()) }
            initializer { PrimitiveKeyValueViewModel(get(), get()) }
            initializer { ObjectKeyValueViewModel(get(), get()) }
            initializer { PlaceholderShowcaseViewModel(get()) }
            initializer { BasicEncryptionViewModel(get(), get()) }
            initializer { PasscodeViewModel(get(), get(), get(), get()) }
            initializer { CoilShowcaseViewModel(get()) }
            initializer { SetPasscodeViewModel(get(), get(), get(), get(), get()) }
            initializer { ResetPasscodeViewModel(get(), get(), get(), get()) }
            initializer { UnlockPasscodeViewModel(get(), get()) }
            initializer { ForgotPasscodeViewModel(get(), get()) }
            initializer { RegisterViewModel(get(), get()) }
            initializer { LoginViewModel(get(), get()) }
        }
    }
}
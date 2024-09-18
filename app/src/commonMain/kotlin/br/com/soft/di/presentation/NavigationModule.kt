package br.com.soft.di.presentation

import br.com.soft.presentation.apartment.ApartmentDestination
import br.com.soft.presentation.dashboard.DashBoardDestination
import br.com.soft.presentation.login.LoginDestination
import br.com.soft.presentation.navigation.samples.a.NavigationADestination
import br.com.soft.presentation.navigation.samples.b.NavigationBDestination
import br.com.soft.presentation.navigation.samples.c.NavigationCDestination
import br.com.soft.presentation.passcode.ui.reset.ResetPasscodeDestination
import br.com.soft.presentation.passcode.ui.set.SetPasscodeDestination
import br.com.soft.presentation.passcode.ui.unlock.UnlockPasscodeDestination
import br.com.soft.presentation.register.RegisterDestination
import br.com.soft.presentation.sharedspaces.SharedSpacesDestination
import br.com.soft.presentation.showcases.ShowcasesDestination
import br.com.soft.presentation.template.screen_with_args.TemplateDestination
import br.com.soft.presentation.template.screen_without_args.TemplateNoArgsDestination
import org.koin.dsl.module
import shared.presentation.navigation.NavigationStore

val navigationModule = module {
    single {
        NavigationStore(
            destinations = listOf(
                ShowcasesDestination,
                TemplateDestination,
                TemplateNoArgsDestination,
                NavigationADestination,
                NavigationBDestination,
                NavigationCDestination,
                SetPasscodeDestination,
                ResetPasscodeDestination,
                UnlockPasscodeDestination,
                RegisterDestination,
                LoginDestination,
                DashBoardDestination,
                ApartmentDestination,
                SharedSpacesDestination
            )
        )
    }
}
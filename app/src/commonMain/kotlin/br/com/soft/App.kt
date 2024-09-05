package br.com.soft

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import br.com.soft.di.get
import br.com.soft.presentation.app.AppScreen
import br.com.soft.presentation.passcode.PasscodeProvider
import br.com.soft.presentation.theme.AppThemeProvider
import shared.presentation.viewmodel.ViewModelProvider

/**
 * Root of the application.
 */
@Composable
fun App() = ViewModelProvider(remember(::get)) {
    AppThemeProvider {
        PasscodeProvider {
            AppScreen()
        }
    }
}
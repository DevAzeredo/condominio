package br.com.soft.di.presentation

import shared.presentation.theme.ThemeConfig
import shared.presentation.theme.ThemeStore
import shared.design.theme.DarkThemeContext
import shared.design.theme.LightThemeContext
import org.koin.dsl.module

val themeModule = module {
    single {
        ThemeStore(
            defaultConfig = ThemeConfig(
                defaultTheme = LightThemeContext,
                lightTheme = LightThemeContext,
                darkTheme = DarkThemeContext
            )
        )
    }
}
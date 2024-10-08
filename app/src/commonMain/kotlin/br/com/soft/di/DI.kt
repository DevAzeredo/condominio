package br.com.soft.di

import br.com.soft.di.data.RepositoryModule
import br.com.soft.di.data.configSourceModule
import br.com.soft.di.data.keyValueSourceModule
import br.com.soft.di.presentation.appModule
import br.com.soft.di.presentation.navigationBarModule
import br.com.soft.di.presentation.navigationModule
import br.com.soft.di.presentation.themeModule
import br.com.soft.platform.configureKoin
import org.koin.core.context.startKoin

val koinApp = startKoin {
    printLogger()
    modules(
        RepositoryModule,
        configSourceModule,
        keyValueSourceModule,
        navigationBarModule,
        navigationModule,
        themeModule,
        appModule
    )
    configureKoin(this)
}

inline fun <reified T : Any> get(): T = koinApp.koin.get<T>()
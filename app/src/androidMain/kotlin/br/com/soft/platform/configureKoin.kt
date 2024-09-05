package br.com.soft.platform

import org.koin.core.KoinApplication
import org.koin.dsl.module

actual fun configureKoin(app: KoinApplication) {
    app.modules(
        module {
        }
    )
}
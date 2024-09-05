package br.com.soft.di.data

import org.koin.dsl.module
import shared.data.source.keyvalue.KeyValueSource
import shared.data.source.keyvalue.SettingsKeyValueSource

val keyValueSourceModule = module {
    single<KeyValueSource> { SettingsKeyValueSource() }
}
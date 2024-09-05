package br.com.soft.di.data

import br.com.soft.data.source.encryption.AppEncryptionSource
import org.koin.dsl.bind
import org.koin.dsl.module
import shared.data.source.encryption.EncryptionSource

val encryptionSourceModule = module {
    single { AppEncryptionSource() }.bind(EncryptionSource::class)
}
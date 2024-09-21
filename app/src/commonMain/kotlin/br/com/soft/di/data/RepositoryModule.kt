package br.com.soft.di.data

import br.com.soft.data.repository.Apartment.ApartmentRepository
import br.com.soft.data.repository.Apartment.ApartmentRepositoryLocalImpl
import br.com.soft.data.source.config.AppConfigSource
import org.koin.dsl.bind
import org.koin.dsl.module
import shared.data.source.config.ConfigSource

val RepositoryModule = module {
    single<ApartmentRepository> { ApartmentRepositoryLocalImpl(get()) }
}
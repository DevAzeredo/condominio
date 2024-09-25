package br.com.soft.di.data

import br.com.soft.data.repository.apartment.ApartmentRepository
import br.com.soft.data.repository.apartment.ApartmentRepositoryLocalImpl
import br.com.soft.data.repository.sharedSpaces.SharedSpaceRepository
import br.com.soft.data.repository.sharedSpaces.SharedSpaceRepositoryLocalImpl
import org.koin.dsl.module

val RepositoryModule = module {
    single<ApartmentRepository> { ApartmentRepositoryLocalImpl(get()) }
    single<SharedSpaceRepository> { SharedSpaceRepositoryLocalImpl(get()) }
}
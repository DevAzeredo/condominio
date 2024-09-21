package br.com.soft.data.repository.Apartment

import br.com.soft.data.model.Apartment
import kotlinx.coroutines.flow.Flow

interface ApartmentRepository {
    fun getApartments(): Flow<List<Apartment>>
    suspend fun addApartment(apartment: Apartment)
    suspend fun removeApartment(apartment: Apartment)
    suspend fun saveChanges()
}
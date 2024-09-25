package br.com.soft.data.repository.sharedSpaces

import br.com.soft.data.model.SharedSpace
import kotlinx.coroutines.flow.Flow

interface SharedSpaceRepository {
    fun getSharedSpace(): Flow<List<SharedSpace>>
    suspend fun addSharedSpace(sharedSpace: SharedSpace)
    suspend fun removeSharedSpace(sharedSpace: SharedSpace)
    suspend fun saveChanges()
}
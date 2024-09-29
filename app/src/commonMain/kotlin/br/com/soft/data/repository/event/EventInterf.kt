package br.com.soft.data.repository.event

import br.com.soft.data.model.Event
import kotlinx.coroutines.flow.Flow


interface EventRepository {
    fun getEvents(): Flow<List<Event>>
    fun setSharedSpaceId(sharedSpaceId: Int)
    suspend fun addEvent(event: Event)
    suspend fun removeEvent(event: Event)
    suspend fun saveChanges(sharedSpaceId: Int)
}
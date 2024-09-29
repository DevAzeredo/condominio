package br.com.soft.data.repository.event

import br.com.soft.data.model.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import shared.data.source.keyvalue.KeyValueSource

class EventRepositoryLocalImpl(
    private val keyValueSource: KeyValueSource,
) : EventRepository {
    private val repositoryJob = Job()
    private val repositoryScope = CoroutineScope(repositoryJob)

    private val eventsFlow = MutableStateFlow<List<Event>>(emptyList())
    private val json = Json { ignoreUnknownKeys = true }
    private var sharedSpaceId: Int = 0
    private var eventsKey = "events_"

    override fun setSharedSpaceId(sharedSpaceId: Int) {
        this.sharedSpaceId = sharedSpaceId
        repositoryScope.launch {
            loadEvents()
        }
    }

    override fun getEvents(): Flow<List<Event>> = eventsFlow.asStateFlow()


    override suspend fun addEvent(event: Event) {
        val currentList = eventsFlow.value.toMutableList()
        if (event.id == 0) {
            event.id = currentList.size + 1
        }
        currentList.add(event)
        eventsFlow.value = currentList
    }

    override suspend fun removeEvent(event: Event) {
        val currentList = eventsFlow.value.toMutableList()
        currentList.remove(event)
        eventsFlow.value = currentList
    }

    override suspend fun saveChanges(sharedSpaceId: Int) {
        val jsonString = json.encodeToString(eventsFlow.value)
        keyValueSource.save(eventsKey + sharedSpaceId, jsonString)
    }

    private suspend fun loadEvents() {
        val jsonString: String = keyValueSource.read<String>(eventsKey + sharedSpaceId).toString()
        val events: List<Event> = try {
            json.decodeFromString(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
        val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val upcomingEvents = events.filter { event ->
            val eventDate = Instant.fromEpochMilliseconds(event.date)
                .toLocalDateTime(TimeZone.currentSystemDefault()).date
            eventDate >= currentDateTime.date
        }
        eventsFlow.value = upcomingEvents
    }
}
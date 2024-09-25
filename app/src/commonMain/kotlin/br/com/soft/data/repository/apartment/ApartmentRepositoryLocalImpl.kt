package br.com.soft.data.repository.apartment

import br.com.soft.data.model.Apartment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import shared.data.source.keyvalue.KeyValueSource

class ApartmentRepositoryLocalImpl(
    private val keyValueSource: KeyValueSource
) : ApartmentRepository {
    private val repositoryJob = Job()
    private val repositoryScope = CoroutineScope(repositoryJob)

    private val apartmentsFlow = MutableStateFlow<List<Apartment>>(emptyList())
    private val json = Json { ignoreUnknownKeys = true }

    private val apartmentsKey = "apartments"

    init {
        repositoryScope.launch {
            loadApartments()
        }
    }

    override fun getApartments(): Flow<List<Apartment>> = apartmentsFlow.asStateFlow()

    override suspend fun addApartment(apartment: Apartment) {
        val currentList = apartmentsFlow.value.toMutableList()
        currentList.add(apartment)
        apartmentsFlow.value = currentList
    }

    override suspend fun removeApartment(apartment: Apartment) {
        val currentList = apartmentsFlow.value.toMutableList()
        currentList.remove(apartment)
        apartmentsFlow.value = currentList
    }

    override suspend fun saveChanges() {
        val jsonString = json.encodeToString(apartmentsFlow.value)
        keyValueSource.save(apartmentsKey, jsonString)
    }

    private suspend fun loadApartments() {
        val jsonString : String = keyValueSource.read<String>(apartmentsKey).toString()
        val apartments: List<Apartment> = try {
            json.decodeFromString(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
        apartmentsFlow.value = apartments
    }
}
package br.com.soft.data.repository.sharedSpaces

import br.com.soft.data.model.SharedSpace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import shared.data.source.keyvalue.KeyValueSource

class SharedSpaceRepositoryLocalImpl(
    private val keyValueSource: KeyValueSource
) : SharedSpaceRepository {
    private val repositoryJob = Job()
    private val repositoryScope = CoroutineScope(repositoryJob)

    private val sharedSpaceFlow = MutableStateFlow<List<SharedSpace>>(emptyList())
    private val json = Json { ignoreUnknownKeys = true }

    private val sharedSpacesKey = "sharedspaces"

    init {
        repositoryScope.launch {
            loadSharedSpaces()
        }
    }

    override fun getSharedSpace(): Flow<List<SharedSpace>> = sharedSpaceFlow.asStateFlow()

    override suspend fun addSharedSpace(sharedSpace: SharedSpace) {
        val currentList = sharedSpaceFlow.value.toMutableList()
        currentList.add(sharedSpace)
        sharedSpaceFlow.value = currentList
    }

    override suspend fun removeSharedSpace(sharedSpace: SharedSpace) {
        val currentList = sharedSpaceFlow.value.toMutableList()
        currentList.remove(sharedSpace)
        sharedSpaceFlow.value = currentList
    }

    override suspend fun saveChanges() {
        val jsonString = json.encodeToString(sharedSpaceFlow.value)
        keyValueSource.save(sharedSpacesKey, jsonString)
    }

    private suspend fun loadSharedSpaces() {
        val jsonString: String = keyValueSource.read<String>(sharedSpacesKey).toString()
        val sharedSpaces: List<SharedSpace> = try {
            json.decodeFromString(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
        sharedSpaceFlow.value = sharedSpaces
    }
}
package br.com.soft.presentation.passcode.usecase

import br.com.soft.presentation.passcode.model.LockState
import br.com.soft.presentation.passcode.model.PasscodeState
import br.com.soft.presentation.passcode.model.PasscodeStore
import kotlinx.datetime.Clock
import shared.data.serialization.SerializationStrategy
import shared.data.source.keyvalue.KeyValueSource

class InitPasscode(
    private val keyValueSource: KeyValueSource,
    private val passcodeStore: PasscodeStore
) {

    suspend fun invoke(): LockState {
        val key = passcodeStore.persistentKey
        val resumeTimeout = passcodeStore.resumeTimeout
        val currentTime = Clock.System.now().toEpochMilliseconds()
        val strategy = SerializationStrategy.json(PasscodeState.serializer())

        val currentLock = passcodeStore.lockState.getNotNull()
        val passcode = keyValueSource.read(key, strategy)
        val lock = when {
            passcode == null -> LockState.UNLOCKED
            currentLock == LockState.UNDEFINED -> LockState.LOCKED
            currentTime - passcode.unlockTime > resumeTimeout -> LockState.LOCKED
            else -> LockState.UNLOCKED
        }

        passcodeStore.passcodeState.set(passcode)
        passcodeStore.lockState.set(lock)

        return lock
    }

}
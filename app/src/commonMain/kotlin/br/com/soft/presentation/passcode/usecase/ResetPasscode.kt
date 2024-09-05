package br.com.soft.presentation.passcode.usecase

import br.com.soft.presentation.passcode.model.LockState
import br.com.soft.presentation.passcode.model.PasscodeState
import br.com.soft.presentation.passcode.model.PasscodeStore
import shared.data.serialization.SerializationStrategy
import shared.data.source.keyvalue.KeyValueSource

class ResetPasscode(
    private val keyValueSource: KeyValueSource,
    private val passcodeStore: PasscodeStore
) {

    suspend fun invoke() {
        val key = passcodeStore.persistentKey
        val strategy = SerializationStrategy.json(PasscodeState.serializer())
        keyValueSource.remove(key, strategy)
        passcodeStore.lockState.set(LockState.UNLOCKED)
        passcodeStore.passcodeState.clear()
    }
}
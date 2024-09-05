package br.com.soft.data.source.config

import shared.data.source.config.DelegateConfigSource

/**
 * This class represents a configuration source for application settings.
 * It delegates to an underlining source and provides methods to retrieve various configuration values
 * without keys passed.
 */
class AppConfigSource : DelegateConfigSource(
    // delegate
) {
    fun getUiLoaderDelay(): Long = getLong("ui_loader_delay") { 50 }
    fun getUiLoaderTimeout(): Long = getLong("ui_loader_timeout") { 30_000 }
}
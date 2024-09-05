package br.com.soft.presentation.navigation

import androidx.compose.runtime.Composable

/**
 * Composable function to provide navigation functionality.
 *
 */
@Composable
fun NavigationBarProvider(content: @Composable () -> Unit) {
    AdaptiveProvider(content)
}
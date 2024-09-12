package br.com.soft.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Apartment(
    var name: String,
    var documento: String,
    var email: String,
    var observacoes: String,
    var numeroContato: String
)
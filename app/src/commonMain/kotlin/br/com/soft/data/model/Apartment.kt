package br.com.soft.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Apartment(
    var id: Int = 0,
    var name: String,
    var documents: String,
    var email: String,
    var observation: String,
    var contact: String
)

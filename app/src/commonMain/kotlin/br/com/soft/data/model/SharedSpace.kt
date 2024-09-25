package br.com.soft.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SharedSpace(
    var id:Int,
    var name: String,
    var documento: String,
    var email: String,
    var observacoes: String,
    var numeroContato: String
)

@Serializable
data class Event(
    val name: String,
    val date: String,
    val time: String
)
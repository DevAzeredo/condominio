package br.com.soft.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SharedSpace(
    var id:Int,
    val name: String,
    val documento: String,
    val email: String,
    val observacoes: String,
    val numeroContato: String
)

@Serializable
data class Event(
    var id: Int,
    val name: String,
    val date: Long,
    val hour: Int,
    val minute:Int
)
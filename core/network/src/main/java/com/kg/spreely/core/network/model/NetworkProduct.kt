package com.kg.spreely.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkProduct(
    val id: Int,
    val name: String,
    val description: String,
    val brand: String,
    val category: String,
    val imageUrl: String,
    val price: Double,
    val quantity: Int,
    val createdAt: String,
    val isAvailable: Boolean,
)

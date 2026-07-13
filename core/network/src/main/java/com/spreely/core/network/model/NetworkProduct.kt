package com.spreely.core.network.model

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

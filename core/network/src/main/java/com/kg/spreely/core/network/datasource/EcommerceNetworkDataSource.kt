package com.kg.spreely.core.network.datasource

import com.kg.spreely.core.network.model.NetworkProduct

interface EcommerceNetworkDataSource {
    suspend fun getProducts(): List<NetworkProduct>

    suspend fun getProductsById(id: Int): NetworkProduct
}

package com.spreely.core.network.datasource

import com.spreely.core.network.model.NetworkProduct

interface EcommerceNetworkDataSource {
    suspend fun getProducts(): List<NetworkProduct>

    suspend fun getProductsById(id: Int): NetworkProduct
}

package com.spreely.core.network.retrofit

import androidx.tracing.trace
import com.spreely.core.network.BuildConfig
import com.spreely.core.network.datasource.EcommerceNetworkDataSource
import com.spreely.core.network.model.NetworkProduct
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitEcommerceNetworkApi {
    @GET(value = "products")
    suspend fun getProducts(): NetworkResponse<List<NetworkProduct>>

    @GET(value = "products/{id}")
    suspend fun getProductsById(
        @Path("id") id: Int
    ): NetworkResponse<NetworkProduct>
}

private const val ECOMMERCE_BASE_URL = BuildConfig.BACKEND_URL

@Serializable
private data class NetworkResponse<T>(val data: T)

@Singleton
internal class RetrofitEcommerceNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : EcommerceNetworkDataSource {

    private val networkApi = trace("RetrofitEcommerceNetwork") {
        Retrofit.Builder()
            .baseUrl(ECOMMERCE_BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitEcommerceNetworkApi::class.java)
    }

    override suspend fun getProducts(): List<NetworkProduct> = networkApi.getProducts().data
    override suspend fun getProductsById(id: Int): NetworkProduct =
        networkApi.getProductsById(id).data
}

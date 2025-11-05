package com.example.cnccalc.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MachineApiService {
    @GET("machines")
    suspend fun getAllMachines(): Response<ApiMachineResponse>

    @GET("machines/search")
    suspend fun searchMachines(
        @Query("q") query: String,
        @Query("manufacturer") manufacturer: String? = null,
        @Query("type") type: String? = null
    ): Response<ApiMachineResponse>

    @GET("machines/popular")
    suspend fun getPopularMachines(): Response<ApiMachineResponse>
}

data class ApiMachineResponse(
    val success: Boolean,
    val data: List<MachineData>,
    val message: String? = null
)

data class MachineData(
    val id: String,
    val name: String,
    val manufacturer: String,
    val model: String,
    val type: String,
    val year: Int,
    val specifications: MachineSpecifications,
    val imageUrl: String? = null
)

data class MachineSpecifications(
    val maxRPM: Int,
    val workingArea: String,
    val toolChangerCapacity: Int,
    val spindlePower: Double,
    val weight: Double,
    val dimensions: String
)
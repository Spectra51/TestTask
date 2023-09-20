package com.flycode.testtask.repository.place

import com.flycode.testtask.model.api.Api
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceRemoteDataSource(
    private val api: Api,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getCities() =
        withContext(ioDispatcher) {
            api.getCities()
        }

    suspend fun getRegions() =
        withContext(ioDispatcher) {
            api.getRegions()
        }

    suspend fun getCountries() =
        withContext(ioDispatcher) {
            api.getCountries()
        }
}
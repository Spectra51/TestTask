package com.flycode.testtask.repository.place

class PlaceRepository(
    private val placeRemoteDataSource: PlaceRemoteDataSource
) {
    suspend fun getCities() = placeRemoteDataSource.getCities()
    suspend fun getRegions() = placeRemoteDataSource.getRegions()
    suspend fun getCountries() = placeRemoteDataSource.getCountries()
}
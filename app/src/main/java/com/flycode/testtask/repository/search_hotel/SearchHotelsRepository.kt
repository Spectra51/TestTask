package com.flycode.testtask.repository.search_hotel

class SearchHotelsRepository(
    private val dataSource: SearchHotelsRemoteDataSource
) {
    suspend fun createSearchRequest(
        startFrom: String,
        startTo: String,
        durationFrom: Int,
        durationTo: Int,
        adults: Int,
        kids: Int,
        departCityId: Int,
        regionIds: ArrayList<Int>
    ) = dataSource.createSearchRequest(
        startFrom = startFrom,
        startTo = startTo,
        durationFrom = durationFrom,
        durationTo = durationTo,
        adults = adults,
        kids = kids,
        departCityId = departCityId,
        regionIds = regionIds
    )

    suspend fun fetchSearchResponse(key:String) = dataSource.fetchSearchResponse(key = key)
}
package com.flycode.testtask.repository.search_hotel

import com.flycode.testtask.model.api.Api
import com.flycode.testtask.model.bodies.Search
import com.flycode.testtask.model.bodies.SearchBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchHotelsRemoteDataSource(
    private val api: Api,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun createSearchRequest(
        startFrom:String,
        startTo:String,
        durationFrom:Int,
        durationTo:Int,
        adults:Int,
        kids:Int,
        departCityId:Int,
        regionIds:ArrayList<Int>
    ) = withContext(ioDispatcher){
        api.createSearch(searchBody = SearchBody(
            search = Search(
                startFrom = startFrom,
                startTo = startTo,
                durationFrom = durationFrom,
                durationTo = durationTo,
                adults = adults,
                kids = kids,
                departCityId = departCityId,
                regionIds = regionIds
            )
        )
        )
    }

    suspend fun fetchSearchResponse(key:String) =
        withContext(ioDispatcher){
            api.getSearchResult(key = key)
        }
}
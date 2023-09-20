package com.flycode.testtask.model.api

import com.flycode.testtask.model.bodies.City
import com.flycode.testtask.model.bodies.Country
import com.flycode.testtask.model.bodies.Region
import com.flycode.testtask.model.bodies.SearchBody
import com.flycode.testtask.model.bodies.SearchResponse
import com.flycode.testtask.model.bodies.SearchResultResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("depart_cities")
    suspend fun getCities() : Response<List<City>>

    @GET("countries")
    suspend fun getCountries() : Response<List<Country>>

    @GET("regions")
    suspend fun getRegions() : Response<List<Region>>

    @POST("searches")
    suspend fun createSearch(@Body searchBody: SearchBody) : Response<SearchResponse>

    @GET("searches/{key}/results")
    suspend fun getSearchResult(@Path("key") key:String) : Response<SearchResultResponse>
}
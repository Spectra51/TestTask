package com.flycode.testtask.model.api

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

interface Place

data class City(
    var id:Int? = null,
    val name:String? = null,
    @SerializedName("country_id")
    val countryId:Int? = null,
    @SerializedName("country_name")
    val countryName:String? = null
) : Place

data class Country(
    val id:Int? = null,
    val name:String? = null,
    val flag:String? = null,
    @SerializedName("visa_required")
    val visaRequired:Boolean? = null,
    @SerializedName("visa_text")
    val visaText:String? = null
) : Place

data class Region(
    val id:Int? = null,
    val name:String? = null,
    val type:String? = null,
    @SerializedName("country_id")
    val countryId:Int? = null,
    @SerializedName("country_name")
    val countryName:String? = null,
    @SerializedName("visa_required")
    val visaRequired:Boolean? = null
) : Place

package com.flycode.testtask.model.bodies

import com.google.gson.annotations.SerializedName

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

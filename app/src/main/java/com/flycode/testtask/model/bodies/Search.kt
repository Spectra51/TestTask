package com.flycode.testtask.model.bodies

import com.google.gson.annotations.SerializedName


data class Search(
    @SerializedName("depart_city_id")
    var departCityId: Int? = null,
    @SerializedName("meal_type_ids")
    var mealTypeIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("region_ids")
    var regionIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("start_from")
    var startFrom: String? = null,
    @SerializedName("start_to")
    var startTo: String? = null,
    @SerializedName("duration_from")
    var durationFrom: Int? = null,
    @SerializedName("duration_to")
    var durationTo: Int? = null,
    var adults: Int? = null,
    var kids: Int? = null,
    var kidsAges: String? = null
)

data class SearchBody(
    var search: Search? = null
)

data class SearchResponse(
    var key:String? = null
)
package com.flycode.testtask.model.bodies

import com.google.gson.annotations.SerializedName


data class SearchResultResponse(
    @SerializedName("results")
    var results: ArrayList<SearchResults> = arrayListOf(),
    @SerializedName("stats")
    var stats: Stats? = Stats()
)
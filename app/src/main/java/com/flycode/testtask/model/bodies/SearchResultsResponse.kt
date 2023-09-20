package com.flycode.testtask.model.bodies

import com.google.gson.annotations.SerializedName


data class SearchResults(

    @SerializedName("offer") var offer: Offer? = Offer(),
    @SerializedName("count") var count: Int? = null

)
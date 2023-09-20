package com.flycode.testtask.model.bodies

import com.google.gson.annotations.SerializedName


data class Stats(
    @SerializedName("status") var status: String? = null,
    @SerializedName("progress") var progress: Int? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("offers_ready") var offersReady: String? = null,
    @SerializedName("hotels_count") var hotelsCount: Int? = null,
    @SerializedName("min_price") var minPrice: MinPrice? = MinPrice()
)
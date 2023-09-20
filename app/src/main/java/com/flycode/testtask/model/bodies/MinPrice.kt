package com.flycode.testtask.model.bodies

import com.google.gson.annotations.SerializedName


data class MinPrice(
    @SerializedName("total") var total: Int? = null,
    @SerializedName("subtotal") var subtotal: Int? = null,
    @SerializedName("original") var original: Int? = null
)
package com.example.example

import com.google.gson.annotations.SerializedName


data class Price (

  @SerializedName("total"         ) var total        : Int?     = null,
  @SerializedName("partner"       ) var partner      : Int?     = null,
  @SerializedName("subtotal"      ) var subtotal     : Int?     = null,
  @SerializedName("original"      ) var original     : Int?     = null,
  @SerializedName("oil_tax"       ) var oilTax       : Int?     = null,
  @SerializedName("insurance_tax" ) var insuranceTax : Int?     = null,
  @SerializedName("markup"        ) var markup       : Int?     = null,
  @SerializedName("low_fee"       ) var lowFee       : Boolean? = null

)
package com.example.example

import com.google.gson.annotations.SerializedName


data class Operator (

  @SerializedName("id"    ) var id    : Int?    = null,
  @SerializedName("name"  ) var name  : String? = null,
  @SerializedName("image" ) var image : String? = null,
  @SerializedName("url"   ) var url   : String? = null

)
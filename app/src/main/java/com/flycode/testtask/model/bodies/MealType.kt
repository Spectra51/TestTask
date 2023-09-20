package com.example.example

import com.google.gson.annotations.SerializedName


data class MealType (

  @SerializedName("original_name" ) var originalName : String? = null,
  @SerializedName("name"          ) var name         : String? = null,
  @SerializedName("id"            ) var id           : Int?    = null

)
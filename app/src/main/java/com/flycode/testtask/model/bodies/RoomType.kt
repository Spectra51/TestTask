package com.example.example

import com.google.gson.annotations.SerializedName


data class RoomType (

  @SerializedName("full_name" ) var fullName : String? = null,
  @SerializedName("name"      ) var name     : String? = null,
  @SerializedName("id"        ) var id       : String? = null

)
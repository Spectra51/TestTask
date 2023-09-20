package com.example.example

import com.google.gson.annotations.SerializedName


data class Checkout (

  @SerializedName("required_fields" ) var requiredFields : ArrayList<String> = arrayListOf()

)
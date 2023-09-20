package com.flycode.testtask.model.bodies

import com.example.example.Checkout
import com.example.example.MealType
import com.example.example.Operator
import com.example.example.OriginalPrice
import com.example.example.Price
import com.example.example.RoomType
import com.example.example.Services
import com.google.gson.annotations.SerializedName


data class Offer(

    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("include_tickets") var includeTickets: Boolean? = null,
    @SerializedName("provider") var provider: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("hotel_id") var hotelId: Int? = null,
    @SerializedName("price") var price: Price? = Price(),
    @SerializedName("original_price") var originalPrice: OriginalPrice? = OriginalPrice(),
    @SerializedName("depart_city_id") var departCityId: Int? = null,
    @SerializedName("region_id") var regionId: Int? = null,
    @SerializedName("country_id") var countryId: Int? = null,
    @SerializedName("adults") var adults: Int? = null,
    @SerializedName("kids") var kids: Int? = null,
    @SerializedName("start_date") var startDate: String? = null,
    @SerializedName("duration") var duration: Int? = null,
    @SerializedName("hotel_duration") var hotelDuration: Int? = null,
    @SerializedName("tour_duration") var tourDuration: Int? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("availability") var availability: String? = null,
    @SerializedName("actualization_available") var actualizationAvailable: Boolean? = null,
    @SerializedName("cost_changed") var costChanged: Boolean? = null,
    @SerializedName("get_tour_link_path") var getTourLinkPath: String? = null,
    @SerializedName("operator") var operator: Operator? = Operator(),
    @SerializedName("room_type") var roomType: RoomType? = RoomType(),
    @SerializedName("meal_type") var mealType: MealType? = MealType(),
    @SerializedName("transport") var transport: String? = null,
    @SerializedName("checkout") var checkout: Checkout? = Checkout(),
    @SerializedName("operator_name") var operatorName: String? = null,
    @SerializedName("operator_compact_name") var operatorCompactName: String? = null,
    @SerializedName("operator_image") var operatorImage: String? = null,
    @SerializedName("operator_url") var operatorUrl: String? = null,
    @SerializedName("services") var services: Services? = Services()

)
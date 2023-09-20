package com.flycode.testtask.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flycode.testtask.model.retrofit.RetrofitClient
import com.flycode.testtask.repository.place.PlaceRemoteDataSource
import com.flycode.testtask.repository.place.PlaceRepository
import com.flycode.testtask.repository.search_hotel.SearchHotelsRemoteDataSource
import com.flycode.testtask.repository.search_hotel.SearchHotelsRepository
import com.flycode.testtask.view.catalog.view_model.CatalogViewModel
import com.flycode.testtask.view.choosing_date.view_model.ChoosingDateViewModel
import com.flycode.testtask.view.countries_dialog.view_model.CountryViewModel

class Factory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountryViewModel(
                repository = PlaceRepository(
                    placeRemoteDataSource = PlaceRemoteDataSource(
                        api = RetrofitClient.service
                    )
                )
            ) as T
        }else if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatalogViewModel(
                repository = SearchHotelsRepository(
                    dataSource = SearchHotelsRemoteDataSource(
                        api = RetrofitClient.service
                    )
                )
            ) as T
        }else if (modelClass.isAssignableFrom(ChoosingDateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChoosingDateViewModel(
                repository = SearchHotelsRepository(
                    dataSource = SearchHotelsRemoteDataSource(
                        api = RetrofitClient.service
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
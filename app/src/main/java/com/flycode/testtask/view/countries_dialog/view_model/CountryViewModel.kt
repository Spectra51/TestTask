package com.flycode.testtask.view.countries_dialog.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flycode.testtask.model.bodies.City
import com.flycode.testtask.model.bodies.Country
import com.flycode.testtask.model.bodies.Region
import com.flycode.testtask.repository.place.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel(
    private val repository: PlaceRepository
) : ViewModel() {

    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
    val countryList: StateFlow<List<Country>>
        get() = _countryList

    private val _regionList = MutableStateFlow<List<Region>>(emptyList())
    val regionList: StateFlow<List<Region>>
        get() = _regionList

    private val _cityList = MutableStateFlow<List<City>>(emptyList())
    val cityList: StateFlow<List<City>>
        get() = _cityList

    private val _isError = MutableStateFlow<Pair<String?, Boolean>>(Pair(null, false))
    val isError: StateFlow<Pair<String?, Boolean>>
        get() = _isError

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCountries()
            if (response.isSuccessful && response.body() != null) {
                _countryList.value = response.body()!!
            } else {
                _isError.value = _isError.value.copy(
                    first = "error",
                    second = true
                )
            }
        }
    }

    fun fetchRegions() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRegions()
            if (response.isSuccessful && response.body() != null) {
                _regionList.value = response.body()!!
            } else {
                _isError.value = _isError.value.copy(
                    first = "error",
                    second = true
                )
            }
        }
    }

    fun fetchCities() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCities()
            if (response.isSuccessful && response.body() != null) {
                _cityList.value = response.body()!!
            } else {
                _isError.value = isError.value.copy(
                    first = "error",
                    second = true
                )
            }
        }
    }

    fun errorShown() {
        _isError.value = isError.value.copy(
            first = isError.value.first,
            second = false
        )
    }
}
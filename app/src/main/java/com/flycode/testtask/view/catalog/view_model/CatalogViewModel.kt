package com.flycode.testtask.view.catalog.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flycode.testtask.model.bodies.SearchResults
import com.flycode.testtask.repository.search_hotel.SearchHotelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val repository: SearchHotelsRepository
) : ViewModel() {
    private val _hotelList = MutableStateFlow<List<SearchResults>>(emptyList())
    val hotelList: StateFlow<List<SearchResults>>
        get() = _hotelList

    private val _isError = MutableStateFlow<Pair<String?, Boolean>>(Pair(null, false))
    val isError: StateFlow<Pair<String?, Boolean>>
        get() = _isError

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    fun fetchHotels(key:String){
        viewModelScope.launch(Dispatchers.IO){
            ensureActive()
            _isLoading.value = true
            val response = repository.fetchSearchResponse(key = key)
            if (response.isSuccessful && response.body() != null){
                if (response.body()?.stats?.progress != 100) {
                    delay(2000)
                    fetchHotels(key = key)
                }else{
                    _hotelList.value = response.body()!!.results
                }
                _isLoading.value = false
            }else{
                _isError.value = isError.value.copy(
                    first = "error",
                    second = true
                )
                _isLoading.value = false
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
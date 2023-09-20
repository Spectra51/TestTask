package com.flycode.testtask.view.choosing_date.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flycode.testtask.repository.search_hotel.SearchHotelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChoosingDateViewModel(
    private val repository: SearchHotelsRepository
) : ViewModel() {

    private val _searchKey = MutableStateFlow<Pair<String?, Boolean>>(Pair(null, false))
    val searchKey: StateFlow<Pair<String?, Boolean>>
        get() = _searchKey

    private val _isError = MutableStateFlow<Pair<String?, Boolean>>(Pair(null, false))
    val isError: StateFlow<Pair<String?, Boolean>>
        get() = _isError

    fun fetchSearchKey(
        startFrom: String,
        startTo: String,
        durationFrom: Int,
        durationTo: Int,
        adults: Int,
        kids: Int,
        departCityId: Int,
        regionIds: ArrayList<Int>
    ){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.createSearchRequest(
                startFrom = startFrom,
                startTo = startTo,
                durationFrom = durationFrom,
                durationTo = durationTo,
                adults = adults,
                kids = kids,
                departCityId = departCityId,
                regionIds = regionIds
            )
            if (response.isSuccessful && response.body() != null){
                _searchKey.value = searchKey.value.copy(
                    first = response.body()!!.key,
                    second = false
                )
            }else{
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

    fun navigationDone(){
        _searchKey.value = searchKey.value.copy(
            second = true
        )
    }
}
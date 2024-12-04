package com.wannacall.countrypicker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wannacall.countrypicker.Countries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class CountryPickerViewModel : ViewModel() {
    var searchQuery by mutableStateOf("")
        private set

    var countries by mutableStateOf(Countries.ALL_COUNTRIES)
        private set

    fun onSearchQueryChange(text: String) {
        searchQuery = text
        if (searchQuery.isEmpty()) {
            countries = Countries.ALL_COUNTRIES
            return
        }
        filterCountries(searchQuery)
    }

    private fun filterCountries(query: String) {
        if (query == searchQuery) {
            val filteredCountries = Countries.ALL_COUNTRIES.filter {
                it.countryName.contains(query, ignoreCase = true) ||
                        it.countryCode.contains(query, ignoreCase = true) ||
                        it.internationalDialCode.contains(query, ignoreCase = true)
            }
            countries = filteredCountries
        }
    }
}

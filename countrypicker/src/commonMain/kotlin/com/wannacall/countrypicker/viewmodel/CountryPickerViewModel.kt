package com.wannacall.countrypicker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wannacall.countrypicker.country.Countries

class CountryPickerViewModel : ViewModel() {
    var searchQuery by mutableStateOf("")
        private set

    var countries by mutableStateOf(Countries.all)
        private set

    fun onSearchQueryChange(text: String) {
        searchQuery = text
        countries = Countries.all.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                    it.code.contains(searchQuery, ignoreCase = true) ||
                    it.dialCode.contains(searchQuery, ignoreCase = true)
        }
    }
}
package com.wannacall.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.wannacall.countrypicker.CountryPickerIcon
import com.wannacall.countrypicker.country.Countries
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        CountryPickerIcon(Countries.`in`)
    }
}
package com.wannacall.countrypicker

import org.jetbrains.compose.resources.DrawableResource

data class Country(
    val countryName: String,
    val countryCode: String,
    val internationalDialCode: String,
    val flagImageResource: DrawableResource
)
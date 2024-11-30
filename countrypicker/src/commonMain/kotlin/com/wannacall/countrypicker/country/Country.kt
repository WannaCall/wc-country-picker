package com.wannacall.countrypicker.country

import org.jetbrains.compose.resources.DrawableResource

data class Country(
    val name: String,
    val code: String,
    val dialCode: String,
    val flag: DrawableResource
)
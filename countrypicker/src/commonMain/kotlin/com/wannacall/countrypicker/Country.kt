package com.wannacall.countrypicker

import org.jetbrains.compose.resources.DrawableResource

/**
 * Data class representing a country in the country picker.
 *
 * This class encapsulates the details of a country, including the country name, country code,
 * international dial code, and a reference to the flag image resource.
 *
 * @property countryName The name of the country (e.g., "United States", "India").
 * @property countryCode The official country code (e.g., "US", "IN").
 * @property internationalDialCode The international dialing code for the country (e.g., "+1", "+91").
 * @property flagImageResource The resource reference for the country's flag image.
 */
data class Country(
    /**
     * The name of the country.
     *
     * This value represents the common name of the country (e.g., "United States", "India").
     */
    val countryName: String,

    /**
     * The country code, which is a short string representing the country (e.g., "US", "IN").
     *
     * This is the ISO 3166-1 alpha-2 country code.
     */
    val countryCode: String,

    /**
     * The international dial code for the country.
     *
     * This code is used for dialing to the country from another country and is typically prefixed with a "+" (e.g., "+1", "+91").
     */
    val internationalDialCode: String,

    /**
     * The drawable resource representing the country's flag image.
     *
     * This image is typically used in the UI to visually represent the country.
     */
    val flagImageResource: DrawableResource
)

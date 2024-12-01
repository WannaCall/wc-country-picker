package com.wannacall.countrypicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.wannacall.countrypicker.country.Country
import org.jetbrains.compose.resources.painterResource

@Composable
fun CountryPickerIcon(
    country: Country
) = Row(
    modifier = Modifier.clickable {
        
    },
    horizontalArrangement = Arrangement.spacedBy(2.dp),
    verticalAlignment = Alignment.CenterVertically,
) {
    Image(
        painter = painterResource(country.flag),
        contentDescription = country.name,
        modifier = Modifier.size(25.dp).clip(CircleShape)
    )

    Text(country.dialCode)

    Icon(
        imageVector = Icons.Default.KeyboardArrowDown,
        contentDescription = "Open country picker"
    )
}
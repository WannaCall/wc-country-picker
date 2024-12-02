package com.wannacall.demo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wannacall.countrypicker.CountryPickerIcon
import com.wannacall.countrypicker.country.Countries
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() = MaterialTheme {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.height(50.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                CountryPickerIcon(Countries.`in`)
            }
        }
    }
}
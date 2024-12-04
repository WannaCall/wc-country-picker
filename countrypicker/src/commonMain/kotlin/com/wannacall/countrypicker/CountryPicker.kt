package com.wannacall.countrypicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wannacall.countrypicker.viewmodel.CountryPickerViewModel
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun CountryPicker(
    viewModel: CountryPickerViewModel = viewModel(),
    onSelection: (Country) -> Unit
) {
    var isSearchEmpty by remember { mutableStateOf(true) }

    LaunchedEffect(viewModel.searchQuery) {
        isSearchEmpty = viewModel.searchQuery.isEmpty()
    }

    Column(modifier = Modifier.padding(12.dp).fillMaxWidth()) {

        TextField(
            value = viewModel.searchQuery,
            onValueChange = viewModel::onSearchQueryChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search countries"
                )
            },
            isError = viewModel.countries.isEmpty() && !isSearchEmpty,
            placeholder = { Text("Enter country name or code") }
        )

        if (viewModel.countries.isEmpty() && !isSearchEmpty) {
            Text("No countries found", modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        Spacer(Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(viewModel.countries) { country ->
                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clickable { onSelection(country) },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(country.flagImageResource),
                            contentDescription = country.countryName,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(30.dp)
                        )

                        Text(text = country.countryName)
                    }

                    Text(text = country.internationalDialCode)
                }
            }
        }
    }
}

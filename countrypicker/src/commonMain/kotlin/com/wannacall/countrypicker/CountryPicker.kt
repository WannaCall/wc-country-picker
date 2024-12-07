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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wannacall.countrypicker.country.Country
import com.wannacall.countrypicker.viewmodel.CountryPickerViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerIcon(
    country: Country,
    onSelection: (Country) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val closeSheet: () -> Unit = {
        coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
            showBottomSheet = false
        }
    }

    Row(
        modifier = Modifier.clickable { showBottomSheet = true },
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(country.flag),
            contentDescription = country.name,
            modifier = Modifier.clip(CircleShape)
        )

        Text(country.dialCode)

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Open country picker"
        )
    }

    if (showBottomSheet) ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState = sheetState
    ) {
        CountryPicker {
            onSelection(it)
            closeSheet()
        }
    }
}

@Composable
internal fun CountryPicker(
    viewModel: CountryPickerViewModel = viewModel { CountryPickerViewModel() },
    onSelection: (Country) -> Unit
) = Column(modifier = Modifier.padding(12.dp).fillMaxWidth()) {
    TextField(
        value = viewModel.searchQuery,
        onValueChange = viewModel::onSearchQueryChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }
    )

    Spacer(Modifier.height(8.dp))

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(viewModel.countries) {
            Row(
                modifier = Modifier.height(45.dp)
                    .fillMaxWidth()
                    .clickable { onSelection(it) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(.8f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(it.flag),
                        contentDescription = it.name,
                        modifier = Modifier.padding(2.dp).clip(CircleShape)
                    )

                    Text(it.name)
                }

                Text(text = it.dialCode)
            }
        }
    }
}
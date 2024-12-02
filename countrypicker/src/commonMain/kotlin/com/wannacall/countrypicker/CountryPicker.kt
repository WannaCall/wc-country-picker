package com.wannacall.countrypicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wannacall.countrypicker.country.Country
import com.wannacall.countrypicker.viewmodel.CountryPickerViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerIcon(
    country: Country
) {
    val coroutineScope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

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
        onDismissRequest = {
            coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                showBottomSheet = false
            }
        },
        sheetState = sheetState
    ) {
        CountryPicker()
    }
}

@Composable
internal fun CountryPicker(
    viewModel: CountryPickerViewModel = viewModel { CountryPickerViewModel() }
) = Column(modifier = Modifier.fillMaxWidth()) {
    TextField(
        value = viewModel.searchQuery,
        onValueChange = viewModel::onSearchQueryChange,
        label = { Text("Search") }
    )

    Spacer(Modifier.height(8.dp))
    Text(viewModel.searchQuery)
    Text(viewModel.countries.size.toString())

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(viewModel.countries) {
            Row(
                modifier = Modifier.height(45.dp)
                    .clickable { /* TODO */ },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(it.flag),
                    contentDescription = it.name,
                    modifier = Modifier.clip(CircleShape)
                )

                Text(it.name)
            }
        }
    }
}
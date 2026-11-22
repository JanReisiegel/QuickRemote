package com.reisiegel.quickremote.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.reisiegel.quickremote.data.repository.LocalRemoteRepository
import com.reisiegel.quickremote.data.repository.RemoteRepository
import com.reisiegel.quickremote.models.AppDestination
import com.reisiegel.quickremote.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRemoteScreen(
    modifier: Modifier = Modifier
){
    val repository = LocalRemoteRepository.current
    var selectedBrand: String by rememberSaveable { mutableStateOf("Není vybrán") }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val brands by remember(searchQuery) {
        if (searchQuery.isBlank()){
            repository.getAllBrands()
        } else {
            repository.searchBrands(searchQuery)
        }
    }.collectAsState(initial = emptyList())

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Selected brand: $selectedBrand")
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = brands,
                key = {brand -> brand}
            ){ brand ->
                BrandElementRow(brand, onClick = { selectedBrand = brand})
            }
        }
    }
}

@Composable
fun BrandElementRow(
    brandName: String,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = brandName, style = MaterialTheme.typography.bodyLarge)
    }
}
package com.reisiegel.quickremote.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.reisiegel.quickremote.models.AppDestination
import com.reisiegel.quickremote.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadRemoteScreen(
    modifier: Modifier = Modifier
){
    var selectedBrand: String = ""

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (selectedBrand.isNotEmpty()) {
            Text(text=selectedBrand)
        } else {
            Text(text="No brand selected")
        }
        Text(text = "Read Remote Screen")
    }
};
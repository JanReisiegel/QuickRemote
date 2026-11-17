package com.reisiegel.quickremote.ui.screen

import androidx.compose.foundation.layout.Column
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
    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(AppDestination.ReadNFC)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(text = "Read Remote Screen")
        }
    }
}
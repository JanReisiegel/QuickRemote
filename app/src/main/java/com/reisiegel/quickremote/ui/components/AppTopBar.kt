package com.reisiegel.quickremote.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.reisiegel.quickremote.R
import com.reisiegel.quickremote.models.AppDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentScreen: AppDestination
) {
    when (currentScreen) {
        AppDestination.Favourites -> FavouritesTopBar()
        AppDestination.AddRemote -> AddRemoteTopBar()
        AppDestination.ReadNFC -> ReadRemoteTopBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavouritesTopBar(){
    TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary
//                ),
        title = { Text(text = "Favourites Remotes") },
        navigationIcon = {
            IconButton(onClick = {/*do something*/}) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "Back"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddRemoteTopBar(){
    TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary
//                ),
        title = { Text(text = "Add Remote") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReadRemoteTopBar(){
    TopAppBar(
        title = { Text(text = "Read Remote") }
    )
}


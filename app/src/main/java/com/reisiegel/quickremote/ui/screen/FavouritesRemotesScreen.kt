package com.reisiegel.quickremote.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.reisiegel.quickremote.R
import com.reisiegel.quickremote.models.AppDestination
import com.reisiegel.quickremote.ui.components.AppTopBar

// Tady bude stránka s listem oblíbených remotu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesRemotesScreen(
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Favourites Remotes Screen")
    }
}

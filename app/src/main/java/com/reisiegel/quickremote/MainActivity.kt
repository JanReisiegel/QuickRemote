package com.reisiegel.quickremote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.reisiegel.quickremote.models.AppDestination
import com.reisiegel.quickremote.ui.components.AppTopBar
import com.reisiegel.quickremote.ui.screen.AddRemoteScreen
import com.reisiegel.quickremote.ui.screen.FavouritesRemotesScreen
import com.reisiegel.quickremote.ui.screen.ReadRemoteScreen
import com.reisiegel.quickremote.ui.theme.QuickRemoteTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickRemoteTheme {
                MainApp()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun RemotesApp(){
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxSize()) {
        Text("Hello, World!")
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppDestination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        AppDestination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    AppDestination.Favourites -> FavouritesRemotesScreen()
                    AppDestination.AddRemote -> AddRemoteScreen()
                    AppDestination.ReadNFC -> ReadRemoteScreen()
                }
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = AppDestination.Favourites
    var selectedDestination by rememberSaveable { mutableStateOf(startDestination) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                AppDestination.entries.forEach {
                    NavigationBarItem(
                        selected = selectedDestination == it,
                        onClick = { selectedDestination = it },
                        icon = { Icon(painterResource(id = it.icon), contentDescription = it.label) },
                        label = {Text(it.label)}
                    )
                }
            }
        },
        topBar = {
            AppTopBar(currentScreen = selectedDestination)
        }
    ) {
        contentPadding -> AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }
}
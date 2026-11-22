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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.reisiegel.quickremote.data.local.CatalogDatabase
import com.reisiegel.quickremote.data.local.UserDatabase
import com.reisiegel.quickremote.data.repository.LocalRemoteRepository
import com.reisiegel.quickremote.data.repository.RemoteRepository
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
            val context = LocalContext.current
            val repository = remember {
                val catalogDb = CatalogDatabase.getInstance(context)

                val userDb = UserDatabase.getInstance(context)
                RemoteRepository(
                    catalogDao = catalogDb.catalogDao(),
                    favouriteDao = userDb.favouriteDao()
                )
            }
            CompositionLocalProvider(LocalRemoteRepository provides repository) {
                QuickRemoteTheme {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppDestination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedDestination = AppDestination.entries.find { it.route == currentRoute } ?: AppDestination.Favourites

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                AppDestination.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = selectedDestination == destination,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(painterResource(id = destination.icon), contentDescription = destination.label) },
                        label = {Text(destination.label)}
                    )
                }
            }
        },
        topBar = {
            AppTopBar(currentScreen = selectedDestination)
        }
    ) { contentPadding ->
        AppNavHost(
            navController = navController,
            startDestination = AppDestination.Favourites,
            modifier = Modifier.padding(contentPadding)
        )
    }
}
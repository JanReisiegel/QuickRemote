package com.reisiegel.quickremote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.reisiegel.quickremote.data.local.CatalogDatabase
import com.reisiegel.quickremote.data.local.UserDatabase
import com.reisiegel.quickremote.data.repository.LocalRemoteRepository
import com.reisiegel.quickremote.data.repository.RemoteRepository
import com.reisiegel.quickremote.ui.theme.QuickRemoteTheme

class RemoteActivity: ComponentActivity() {
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
                    ShowRemote()
                }
            }
        }
    }
}

@Composable
fun ShowRemote(
    modifier: Modifier = Modifier
){

}
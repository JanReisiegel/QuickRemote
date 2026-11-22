package com.reisiegel.quickremote.data.repository

import androidx.compose.runtime.staticCompositionLocalOf

val LocalRemoteRepository = staticCompositionLocalOf<RemoteRepository> {
    error("LocalRemoteRepository not provided")
}
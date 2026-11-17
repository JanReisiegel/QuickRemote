package com.reisiegel.quickremote.models

import com.reisiegel.quickremote.R

enum class AppDestination(val route: String, val label: String, val icon: Int) {
    Favourites("favourites", "Favourites", R.drawable.ic_favourites),
    AddRemote("add_remote", "Create", R.drawable.ic_add),
    ReadNFC("read_nfc", "Read Remote", R.drawable.ic_nfc)
}
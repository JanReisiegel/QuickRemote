package com.reisiegel.quickremote.data.repository

import com.reisiegel.quickremote.data.local.entity.CatalogRemoteEntity
import com.reisiegel.quickremote.data.local.entity.FavouriteRemoteEntity

data class FavouriteRemoteDetail(
    val favourite: FavouriteRemoteEntity,
    val catalogData: CatalogRemoteEntity
)
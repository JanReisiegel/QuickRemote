package com.reisiegel.quickremote.data.local.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity

@Entity(
    tableName = "favourite_remotes",
    primaryKeys = ["brand", "name"],
)
data class FavouriteRemoteEntity(
    val brand: String,
    val name: String,
    @ColumnInfo(name = "custom_name") val customName: String? = null,
    @ColumnInfo(name = "added_at") val addedAt: Long = System.currentTimeMillis()
)
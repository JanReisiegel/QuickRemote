package com.reisiegel.quickremote.data.local.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "catalog_buttons",
    foreignKeys = [
        ForeignKey(
            entity = CatalogRemoteEntity::class,
            parentColumns = ["brand", "name"],
            childColumns = ["remote_brand", "remote_name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["remote_id"])]
)
data class CatalogButtonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "remote_brand") val remoteBrand: String,
    @ColumnInfo(name = "remote_name") val remoteName: String,
    val name: String,
    val code: String
)
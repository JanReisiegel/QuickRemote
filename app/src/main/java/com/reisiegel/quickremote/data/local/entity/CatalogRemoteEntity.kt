package com.reisiegel.quickremote.data.local.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "catalog_remotes",
    primaryKeys = ["brand", "name"],
    indices = [Index(value = ["brand"])]
)
data class CatalogRemoteEntity(
    val brand: String,
    val name: String,
    val frequency: Int,
    val bits: Int,
    val flags: String,
    val eps: Int,
    val aeps: Int,
    @ColumnInfo(name = "header_pulse") val headerPulse: Int,
    @ColumnInfo(name = "header_space") val headerSpace: Int,
    @ColumnInfo(name = "one_pulse") val onePulse: Int,
    @ColumnInfo(name = "one_space") val oneSpace: Int,
    @ColumnInfo(name = "zero_pulse") val zeroPulse: Int,
    @ColumnInfo(name = "zero_space") val zeroSpace: Int,
    val ptrail: Int,
    val plead: Int,
    val gap: Int,
    @ColumnInfo(name = "pre_data_bits") val preDataBits: Int,
    @ColumnInfo(name = "pre_data") val preData: String?,
    @ColumnInfo(name = "post_data_bits") val postDataBits: Int,
    @ColumnInfo(name = "post_data") val postData: String?,
    @ColumnInfo(name = "min_repeat") val minRepeat: Int
)
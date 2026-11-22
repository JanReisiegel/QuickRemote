package com.reisiegel.quickremote.data.local

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.reisiegel.quickremote.data.local.dao.CatalogDao
import com.reisiegel.quickremote.data.local.entity.CatalogButtonEntity
import com.reisiegel.quickremote.data.local.entity.CatalogRemoteEntity

@Database(
    entities = [
        CatalogRemoteEntity::class,
        CatalogButtonEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CatalogDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogDao
    companion object {
        fun getInstance(context: Context): CatalogDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CatalogDatabase::class.java,
                "remotes_catalog.db"
            )
                .createFromAsset("database/remotes_catalog.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
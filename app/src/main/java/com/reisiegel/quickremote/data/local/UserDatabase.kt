package com.reisiegel.quickremote.data.local

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.reisiegel.quickremote.data.local.dao.FavouriteDao
import com.reisiegel.quickremote.data.local.entity.FavouriteRemoteEntity

@Database(
    entities = [FavouriteRemoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_remote.db"
                ).build()
                .also { INSTANCE = it }
            }
        }
    }
}

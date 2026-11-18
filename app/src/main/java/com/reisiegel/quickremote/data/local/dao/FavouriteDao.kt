package com.reisiegel.quickremote.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.reisiegel.quickremote.data.local.entity.FavouriteRemoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    /**
     * Shows all favourite remotes
     *
     * @return [Flow] emitting List of favourite remotes
     */
    @Query("SELECT * FROM favourite_remotes ORDER BY added_at DESC")
    fun getAllFavourites(): Flow<List<FavouriteRemoteEntity>>

    /**
     * Add remote to favourites remotes
     *
     * @param favourite The favourite remote to add
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(favourite: FavouriteRemoteEntity)

    /**
     * Remove remote from favourite remotes
     *
     * @param brand Remote Brand
     * @param name Remote name
     */
    @Query("DELETE FROM favourite_remotes WHERE brand = :brand AND name = :name")
    suspend fun removeFavourite(brand: String, name: String)

    /**
     * Determines if the remote is favourite
     *
     * @param brand Remote Brand
     * @param name Remote name
     * @return [Flow] emitting `true` if the remote is favourite, `false` otherwise
     */
    @Query("SELECT EXISTS(SELECT 1 FROM favourite_remotes WHERE brand = :brand AND name = :name)")
    fun isFavourite(brand: String, name: String): Flow<Boolean>

    /**
     * Renames the saved favourite remote (e.g. to "TV Bedroom").
     *
     * @param brand Remote Brand
     * @param name Remote name
     */
    @Query("UPDATE favourite_remotes SET custom_name = :customName WHERE brand = :brand AND name = :name")
    suspend fun renameFavourite(brand: String, name: String, customName: String?)
}
package com.reisiegel.quickremote.data.local.dao

import androidx.room3.Dao
import androidx.room3.Query
import com.reisiegel.quickremote.data.local.entity.CatalogButtonEntity
import com.reisiegel.quickremote.data.local.entity.CatalogRemoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogDao {

    /**
     * Shows all brand of remotes
     *
     * @return [Flow] emitting List of brands
     */
    @Query("SELECT DISTINCT brand FROM catalog_remotes ORDER BY brand ASC")
    fun getAllBrands(): Flow<List<String>>

    /**
     * Vyhledá unikátní značky odpovídající zadanému textovému řetězci (bez ohledu na velikost písmen).
     *
     * @param query text to search for
     * @return [Flow] emitting List of matching brands
     */
    @Query("SELECT DISTINCT brand FROM catalog_remotes WHERE brand LIKE '%' || :query || '%' ORDER BY brand ASC")
    fun searchBrands(query: String): Flow<List<String>>

    /**
     * Full-text search for drivers matching the brand or model name.
     * The result is limited to the first 50 entries for UI performance reasons.
     *
     * @param query text to search for
     * @return [Flow] emitting List of matching drivers.
     */
    @Query("SELECT * FROM catalog_remotes WHERE brand LIKE '%' || :query || '%' OR name LIKE '%' || :query || '%' ORDER BY brand ASC, name ASC LIMIT 50")
    fun searchRemotes(query: String): Flow<List<CatalogRemoteEntity>>

    /**
     * Retrieves a single remote by its ID.
     *
     * @param brand Brand of remote.
     * @param name Remote name.
     * @return Instance [CatalogRemoteEntity] or `null`, if remoteId doesn't exist
     */
    @Query("SELECT * FROM catalog_remotes WHERE brand = :brand AND name = :name")
    suspend fun getRemote(brand: String, name: String): CatalogRemoteEntity?

    /**
     * Retrieves list of all buttons for remote
     *
     * @param brand Brand of remote
     * @param name Remote name
     * @return List of buttons [CatalogButtonEntity]
     */
    @Query("SELECT * FROM catalog_buttons WHERE remote_brand = :brand AND remote_name = :name ORDER BY id ASC")
    suspend fun getButtonsForRemote(brand: String, name: String): List<CatalogButtonEntity>
}
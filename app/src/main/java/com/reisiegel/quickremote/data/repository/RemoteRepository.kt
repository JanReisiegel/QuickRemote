package com.reisiegel.quickremote.data.repository

import com.reisiegel.quickremote.data.local.dao.CatalogDao
import com.reisiegel.quickremote.data.local.dao.FavouriteDao
import com.reisiegel.quickremote.data.local.entity.CatalogRemoteEntity
import kotlinx.coroutines.flow.Flow

class RemoteRepository(
    private val catalogDao: CatalogDao,
    private val favouriteDao: FavouriteDao
) {
    fun getAllBrands(): Flow<List<String>> = catalogDao.getAllBrands()

    fun searchBrands(query: String): Flow<List<String>> = catalogDao.searchBrands(query)

    fun getRemotesByBrand(brand: String): Flow<List<String>> = catalogDao.getBrandRemotes(brand)

    fun searchRemote(query: String): Flow<List<String>> = catalogDao.searchRemotes(query)
}
package com.example.movieflik.network.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemFavoritDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahItemFavorit(itemFavorit: ItemFavorit)

    @Query("SELECT * FROM ItemFavorit")
    fun getAllFavorite(): LiveData<MutableList<ItemFavorit>>

    @Query("SELECT EXISTS(SELECT id FROM ItemFavorit WHERE id = :id)")
    fun isFavoriteItem(id: Int): Boolean

    @Delete
    fun hapusItemFavorit(itemFavorit: ItemFavorit)
}
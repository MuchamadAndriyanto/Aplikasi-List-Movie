package com.example.movieflik.network.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemFavorit::class], version = 1, exportSchema = false)
abstract class ItemFavoritDatabase : RoomDatabase() {
    abstract fun itemFavoritDao(): ItemFavoritDao
}
package com.example.movieflik.network.local

import android.content.Context
import androidx.room.Room

class DbModule(private val context: Context) {
    private val db = Room.databaseBuilder(context, ItemFavoritDatabase::class.java, "itemfavorit.db")
        .allowMainThreadQueries()
        .build()

    val itemFavoritDao = db.itemFavoritDao()
}

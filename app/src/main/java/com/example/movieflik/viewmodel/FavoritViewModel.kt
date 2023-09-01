package com.example.movieflik.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieflik.model.MovieDetail
import com.example.movieflik.network.local.DbModule
import com.example.movieflik.network.local.ItemFavorit

class FavoritViewModel(application: Application) : AndroidViewModel(application) {
    private val itemFavoritDao = DbModule(application.applicationContext).itemFavoritDao

    fun tambahItemFavorit(itemFavorit: MovieDetail) {
        val favoriteItem = ItemFavorit(
            itemFavorit.id,
            itemFavorit.title,
            itemFavorit.date,
            itemFavorit.imagepath,
            itemFavorit.overview
        )
        itemFavoritDao.tambahItemFavorit(favoriteItem)
    }

    fun getAllFavorite(): LiveData<MutableList<ItemFavorit>> {
        return itemFavoritDao.getAllFavorite()
    }

    fun isFavoriteMovie(id: Int): Boolean {
        return itemFavoritDao.isFavoriteItem(id)
    }

    fun deleteFavorite(itemFavorit: MovieDetail) {
        val favoriteItem = ItemFavorit(
            itemFavorit.id,
            itemFavorit.title,
            itemFavorit.date,
            itemFavorit.imagepath,
            itemFavorit.overview
        )
        itemFavoritDao.hapusItemFavorit(favoriteItem)
    }
}
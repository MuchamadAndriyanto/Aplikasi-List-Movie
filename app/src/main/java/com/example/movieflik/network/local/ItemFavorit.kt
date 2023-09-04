package com.example.movieflik.network.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ItemFavorit(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "release_date")
    var releaseDate: String,
    @ColumnInfo(name = "path_poster")
    var pathPoster: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "popularty")
    var popularty: Double,
) : Parcelable
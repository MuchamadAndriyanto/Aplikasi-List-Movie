package com.example.movieflik.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetail(
    val id: Int,
    val imagepath: String,
    val title: String,
    val date: String,
    val popularity: Double,
    val overview: String,
    var isFavorite: Boolean = false
) : Parcelable
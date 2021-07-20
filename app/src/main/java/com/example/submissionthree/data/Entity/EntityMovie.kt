package com.example.submissionthree.data.Entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_entities")
@Parcelize
data class EntityMovie(
    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "releasedate")
    var releaseDate: String = "",

    @ColumnInfo(name = "score")
    var score: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "imagePath")
    var imagePath: String = "",

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = ""
) : Parcelable
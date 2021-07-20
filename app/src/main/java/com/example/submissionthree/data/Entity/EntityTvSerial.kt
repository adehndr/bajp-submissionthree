package com.example.submissionthree.data.Entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tv_serial_entities")
@Parcelize
data class EntityTvSerial(
    @ColumnInfo(name = "title")
    @SerializedName("original_name")
    var title: String = "",

    @ColumnInfo(name = "releasedate")
    @SerializedName("first_air_date")
    var releaseDate: String = "",

    @ColumnInfo(name = "score")
    @SerializedName("vote_average")
    var score: String = "",

    @ColumnInfo(name = "description")
    @SerializedName("overview")
    var description: String = "",

    @ColumnInfo(name = "imagePath")
    @SerializedName("poster_path")
    var imagePath: String = "",

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = ""
) : Parcelable
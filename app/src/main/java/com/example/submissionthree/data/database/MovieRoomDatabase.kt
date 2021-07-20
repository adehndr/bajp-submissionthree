package com.example.submissionthree.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial

@Database(entities = [EntityMovie::class, EntityTvSerial::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile

        private var INSTANCE: MovieRoomDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): MovieRoomDatabase =
            INSTANCE ?: synchronized(this)
            {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "Movies.db"
                )
            }.build().apply {
                INSTANCE = this
            }
    }
}
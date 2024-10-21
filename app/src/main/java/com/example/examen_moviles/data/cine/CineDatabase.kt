package com.example.examen_moviles.data.cine

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Cine::class], version = 1, exportSchema = false)
abstract class CineDatabase : RoomDatabase() {
    abstract fun cineDao(): CineDao

    companion object{
        @Volatile
        private var Instance: CineDatabase? = null

        fun getDatabase(context: Context): CineDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CineDatabase::class.java, "cine_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
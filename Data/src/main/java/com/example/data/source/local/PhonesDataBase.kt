package com.example.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
 import com.example.data.source.local.dao.PhonesDao
import com.example.domain.model.ResultsItem

@Database(entities = [ResultsItem::class], version = 1, exportSchema = false)
abstract class PhonesDataBase : RoomDatabase() {
    abstract fun phonesDao(): PhonesDao
    companion object {
        @Volatile private var instance: PhonesDataBase? = null
        val DB_NAME = "phones_database"

        fun getDatabase(context: Context): PhonesDataBase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, PhonesDataBase::class.java,  DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}

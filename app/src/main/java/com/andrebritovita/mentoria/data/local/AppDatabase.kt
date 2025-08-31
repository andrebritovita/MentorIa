package com.andrebritovita.mentoria.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrebritovita.mentoria.data.local.dao.StudyPlanDao
import com.andrebritovita.mentoria.data.local.entities.StudyPlanEntity

@Database(entities = [StudyPlanEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studyPlanDao(): StudyPlanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "study_plan_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
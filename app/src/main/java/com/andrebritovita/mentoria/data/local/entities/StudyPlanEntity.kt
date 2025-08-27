package com.andrebritovita.mentoria.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity (tableName = "study_plans", indices = [Index(value = ["topic"], unique = true)])
data class StudyPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val topic: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)
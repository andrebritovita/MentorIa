package com.andrebritovita.mentoria.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrebritovita.mentoria.data.local.entities.StudyPlanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyPlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudyPlan(studyPlan: StudyPlanEntity)

    @Query("SELECT * FROM study_plans ORDER BY timestamp DESC")
    fun getAllStudyPlans(): Flow<List<StudyPlanEntity>>

    @Query("SELECT * FROM study_plans WHERE topic = :topic ORDER BY timestamp DESC LIMIT 1")
    suspend fun getStudyPlanByTopic(topic: String): StudyPlanEntity?

    @Query("DELETE FROM study_plans WHERE topic = :topic")
    suspend fun deleteByTopic(topic: String)
}
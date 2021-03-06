package com.example.projectt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PrivateLessonDao {
    @Insert
    suspend fun insert(privateLesson: PrivateLesson)

    @Insert
    fun insertAll(privateLesson: List<PrivateLesson>)

    @Update
    suspend  fun update(privateLesson: PrivateLesson)

    @Query("DELETE FROM private_lesson")
    fun clear()

    @Query("SELECT * FROM private_lesson")
    fun getAll(): List<PrivateLesson>

    @Query("SELECT * FROM private_lesson")
    fun getAllPrivateLessons(): List<PrivateLesson>

    @Query("SELECT * FROM private_lesson where private_lesson_id= :id")
    fun getPrivateLesson(id: Int): List<PrivateLesson>
}
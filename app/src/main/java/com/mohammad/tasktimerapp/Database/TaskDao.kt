package com.mohammad.tasktimerapp.Database

import androidx.room.*

@Dao
interface TaskDao {
    @Query ("SELECT * FROM Task ORDER by id")
    fun getAllTasks(): List<Task>

    @Insert
    fun insertTask(task:Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}
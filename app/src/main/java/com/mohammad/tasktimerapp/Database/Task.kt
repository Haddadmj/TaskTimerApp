package com.mohammad.tasktimerapp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class Task (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="id") val id:Int,
    @ColumnInfo(name="Name") val name:String,
    @ColumnInfo(name="Description") val description:String,
    @ColumnInfo(name="PauseOff") var pauseOff: Long = 0, // as a value
    @ColumnInfo(name="Time") var stringTime:String = "00:00" //as a string
)
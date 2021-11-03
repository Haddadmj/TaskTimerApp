package com.mohammad.tasktimerapp

import com.google.firebase.Timestamp

data class Task(
    val _taskName: String,
    val taskDescription: String,
    val timestamp: Timestamp,
    val totalTime: Int
) {
    constructor() : this(
        "", "", Timestamp.now(), 0
    )
}

package com.mohammad.tasktimerapp

import com.google.firebase.Timestamp

data class Task(
    val _taskName: String,
    val taskDescription: String,
    val timestamp: Timestamp,
    var totalTime: Long,
    var totalTimeSt: String
) {
    constructor() : this(
        "", "", Timestamp.now(), 0,"00:00"
    )
}

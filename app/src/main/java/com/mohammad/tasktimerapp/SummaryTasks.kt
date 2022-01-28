package com.mohammad.tasktimerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.tasktimerapp.Database.Task
import com.mohammad.tasktimerapp.Database.TaskDao
import com.mohammad.tasktimerapp.Database.TaskDatabase


class SummaryTasks : AppCompatActivity() {
    lateinit var mainRV: RecyclerView
    lateinit var taskDao:TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_tasks)

        taskDao = TaskDatabase.getDatabase(this).taskDao()

        mainRV = findViewById(R.id.rvMain)
        mainRV.adapter = RVAdapte(taskDao.getAllTasks())
        mainRV.layoutManager = LinearLayoutManager(this)

    }
}
package com.mohammad.tasktimerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var addTaskView: Button
    lateinit var viewTasksView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addTaskView = findViewById(R.id.addTaskView)
        addTaskView.setOnClickListener {
            startActivity(Intent(this, AddTask::class.java))
            finish()
        }

        viewTasksView = findViewById(R.id.viewTaskView)
        viewTasksView.setOnClickListener {
            startActivity(Intent(this, ViewTasks::class.java))
            finish()
        }
    }
}
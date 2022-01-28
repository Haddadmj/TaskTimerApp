package com.mohammad.tasktimerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mohammad.tasktimerapp.Database.Task
import com.mohammad.tasktimerapp.Database.TaskDao
import com.mohammad.tasktimerapp.Database.TaskDatabase

class AddTask : AppCompatActivity() {

    lateinit var etTaskName: EditText
    lateinit var etTaskDescription: EditText
    lateinit var addTaskButton: Button
    lateinit var taskDao: TaskDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        supportActionBar?.hide()

        taskDao = TaskDatabase.getDatabase(this).taskDao()

        etTaskName = findViewById(R.id.etTaskName)
        etTaskDescription = findViewById(R.id.etTaskDescription)
        addTaskButton = findViewById(R.id.addTaskBtn)

        addTaskButton.setOnClickListener {
            if (etTaskDescription.text.isNotEmpty() && etTaskName.text.isNotEmpty()) {
                taskDao.insertTask(Task(0,etTaskName.text.toString(),etTaskDescription.text.toString()))
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Task Name or Task Description is Empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
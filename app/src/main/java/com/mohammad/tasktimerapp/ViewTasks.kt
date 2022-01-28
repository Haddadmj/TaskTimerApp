package com.mohammad.tasktimerapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.tasktimerapp.Database.Task
import com.mohammad.tasktimerapp.Database.TaskDao
import com.mohammad.tasktimerapp.Database.TaskDatabase

class ViewTasks : AppCompatActivity() {
    lateinit var mainRV: RecyclerView
    lateinit var taskDao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)

        taskDao = TaskDatabase.getDatabase(this).taskDao()

        mainRV = findViewById(R.id.rvMain)
        updateRV(taskDao.getAllTasks())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.signle_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.showTasks -> {
                startActivity(Intent(this,SummaryTasks::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateRV(list: List<Task>) {
        mainRV.adapter = RVAdapter(list, this)
        mainRV.layoutManager = LinearLayoutManager(this)
    }

    fun update(task:Task){
        taskDao.updateTask(task)
    }
}
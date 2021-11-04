package com.mohammad.tasktimerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewTasks : AppCompatActivity() {
    lateinit var mainRV: RecyclerView
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)
        mainRV = findViewById(R.id.rvMain)
        getData()
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
        db.collection("Tasks")
            .get()
            .addOnSuccessListener {
                for (document in it)
                    if (document.data["timestamp"] == task.timestamp)
                        db.collection("Tasks").document(document.id).update("totalTime",task.totalTime,"totalTimeSt",task.totalTimeSt)
            }
            .addOnFailureListener {  }
    }


    private fun getData(){
        db.collection("Tasks")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                updateRV(it.toObjects(Task::class.java))
            }
            .addOnFailureListener {
                Log.d("Firebase", "Failed to get data")
            }
    }
}
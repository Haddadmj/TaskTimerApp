package com.mohammad.tasktimerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SummaryTasks : AppCompatActivity() {
    lateinit var mainRV: RecyclerView
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_tasks)
        mainRV = findViewById(R.id.rvMain)
        getData()
    }

    private fun updateRV(list: List<Task>) {
        mainRV.adapter = RVAdapte(list)
        mainRV.layoutManager = LinearLayoutManager(this)
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
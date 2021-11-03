package com.mohammad.tasktimerapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewTasks : AppCompatActivity() {
    lateinit var mainRV: RecyclerView
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)
        mainRV = findViewById(R.id.rvMain)
        getData()
    }

    private fun updateRV(list: List<Task>) {
        mainRV.adapter = RVAdapter(list)
        mainRV.layoutManager = LinearLayoutManager(this)
    }


    fun getData(){
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
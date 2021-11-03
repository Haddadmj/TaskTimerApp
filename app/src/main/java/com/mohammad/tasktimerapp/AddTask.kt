package com.mohammad.tasktimerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddTask : AppCompatActivity() {

    lateinit var etTaskName: EditText
    lateinit var etTaskDescription: EditText
    lateinit var addTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        etTaskName = findViewById(R.id.etTaskName)
        etTaskDescription = findViewById(R.id.etTaskDescription)
        addTaskButton = findViewById(R.id.addTaskBtn)
        val db = Firebase.firestore

        addTaskButton.setOnClickListener {
            if (etTaskDescription.text.isNotEmpty() && etTaskName.text.isNotEmpty()) {
                db.collection("Tasks")
                    .add(
                        hashMapOf(
                            "_taskName" to etTaskName.text.toString(),
                            "taskDescription" to etTaskDescription.text.toString(),
                            "totalTime" to 0L,
                            "totalTimeSt" to "00:00",
                            "timestamp" to FieldValue.serverTimestamp()

                        )
                    )
                    .addOnSuccessListener {
                        Log.d("Firebase","Added Successfully")
                        Toast.makeText(applicationContext, "Added Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        Log.d("Firebase","Not Added Successfully")
                    }
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
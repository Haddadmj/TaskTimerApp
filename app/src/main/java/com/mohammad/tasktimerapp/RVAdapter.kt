package com.mohammad.tasktimerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.tasktimerapp.databinding.TaskRowBinding

class RVAdapter(val list: List<Task>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(val binding: TaskRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        return ViewHolder(
            TaskRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        val task = list[position]
        holder.binding.apply {
            tvTaskName.text = task._taskName
            cvTask.setOnClickListener {
                Toast.makeText(holder.itemView.context, task.taskDescription, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = list.size

}

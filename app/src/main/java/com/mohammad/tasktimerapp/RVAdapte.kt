package com.mohammad.tasktimerapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.tasktimerapp.databinding.TaskRowBinding

class RVAdapte(private val list: List<Task>) : RecyclerView.Adapter<RVAdapte.ViewHolder>() {
    class ViewHolder(val binding: TaskRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TaskRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = list[position]

        val countDownTimer = Chronometer(holder.itemView.context)
        countDownTimer.gravity = 17
        countDownTimer.text = task.totalTimeSt
        holder.binding.apply {
            var running = false
            tvTaskName.text = "${task._taskName}\n${task.taskDescription}"
            Log.d("Timer", "onBindViewHolder: ${tvTaskName.gravity}")
            llCard.addView(countDownTimer)
        }
    }

    override fun getItemCount(): Int = list.size

}

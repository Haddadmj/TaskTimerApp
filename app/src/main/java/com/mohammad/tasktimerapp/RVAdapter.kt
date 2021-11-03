package com.mohammad.tasktimerapp

import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.tasktimerapp.databinding.TaskRowBinding

class RVAdapter(private val list: List<Task>, private val activity: ViewTasks) :
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private var runningTask: Chronometer? = null
    private var running = false

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
        countDownTimer.isVisible = false
        holder.binding.apply {
            tvTaskName.text = task._taskName
            llCard.addView(countDownTimer)
            cvTask.setOnClickListener {

                if (!running && runningTask == null) {
                    countDownTimer.isVisible = true
                    countDownTimer.base = SystemClock.elapsedRealtime() - task.totalTime
                    countDownTimer.start()
                    runningTask = countDownTimer
                    running = true

                } else {
                    runningTask?.stop()
                    countDownTimer.isVisible =true
                    countDownTimer.base = SystemClock.elapsedRealtime() - task.totalTime
                    countDownTimer.start()
                    runningTask = countDownTimer
                    Log.d("Timer", "runningTask: ${runningTask?.text} ")
                }
            }
        }
    }


    override fun getItemCount(): Int = list.size

}

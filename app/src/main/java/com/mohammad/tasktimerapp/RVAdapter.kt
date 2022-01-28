package com.mohammad.tasktimerapp

import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.tasktimerapp.Database.Task
import com.mohammad.tasktimerapp.databinding.TaskRowBinding

class RVAdapter(private val list: List<Task>, private val activity: ViewTasks) :
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private var runningTask: Chronometer? = null
    private var oldTask: Task? = null

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
        countDownTimer.gravity = 17
        holder.binding.apply {
            var running = false
            tvTaskName.text = "${task.name} : ${task.description}"
            llCard.addView(countDownTimer)
            cvTask.setOnClickListener {
                running = !running
                countDownTimer.isVisible = true

                if (runningTask == null) {
                    startStopSelf(running, countDownTimer, task)
                    runningTask = countDownTimer
                    oldTask = task

                } else {
                    if (runningTask == countDownTimer) {
                        startStopSelf(running, countDownTimer, task)
                    } else {
                        startStopB(countDownTimer, task)
                    }
                }
            }
        }
    }

    private fun startStopB(
        countDownTimer: Chronometer,
        task: Task
    ) {
        runningTask?.stop()
        oldTask!!.pauseOff = SystemClock.elapsedRealtime() - runningTask!!.base
        oldTask!!.stringTime = runningTask!!.text.toString()
        activity.update(oldTask!!)
        countDownTimer.base = SystemClock.elapsedRealtime() - task.pauseOff
        countDownTimer.start()
        oldTask = task
        runningTask = countDownTimer
    }

    private fun startStopSelf(
        running: Boolean,
        countDownTimer: Chronometer,
        task: Task
    ) {
        if (running) {
            countDownTimer.base = SystemClock.elapsedRealtime() - task.pauseOff
            countDownTimer.start()
        } else {
            countDownTimer.stop()
            task.pauseOff = SystemClock.elapsedRealtime() - countDownTimer.base
            task.stringTime = countDownTimer.text.toString()
            activity.update(task)
        }
    }

    override fun getItemCount(): Int = list.size
}

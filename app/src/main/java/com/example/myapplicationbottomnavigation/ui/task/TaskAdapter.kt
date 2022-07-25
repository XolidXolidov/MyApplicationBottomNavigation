package com.example.myapplicationbottomnavigation.ui.task

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationbottomnavigation.R
import com.example.myapplicationbottomnavigation.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(
    private val onClick: (TaskModel) -> Unit,
    private val onLongClick: (TaskModel) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<TaskModel>()

    fun addTask(new: TaskModel) {
        tasks.add(0, new)
        notifyItemInserted(tasks.lastIndex)
    }

    fun editTask(new: TaskModel) {
        val index = tasks.indexOfFirst { it.time == new.time }
        tasks[index] = new
        notifyItemChanged(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun removeTask(task: TaskModel) {
        val index = tasks.indexOfFirst {
            it.task == task.task
        }
        tasks.removeAt(index)
        notifyItemRemoved(index)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDate(millis: Long, dateFormat: String): String {
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        return simpleDateFormat.format(Date(millis)).toString()
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.M)
        fun onBind(model: TaskModel) {
            val isThemeColor = adapterPosition % 2 == 0
            val color = if (isThemeColor) {
                R.color.white
            } else {
                R.color.white
            }
            itemView.setBackgroundColor(itemView.context.getColor(color))
            binding.txtInput.text = model.task
            binding.txtDate.text = convertDate(model.time, "dd-MMMM-yyyy hh:mm")

            itemView.setOnClickListener {
                onClick(model)
            }
            itemView.setOnLongClickListener {
                onLongClick(model)
                return@setOnLongClickListener true
            }
        }
    }
}
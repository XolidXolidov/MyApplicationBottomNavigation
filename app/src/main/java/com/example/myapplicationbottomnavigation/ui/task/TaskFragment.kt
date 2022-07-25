package com.example.myapplicationbottomnavigation.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.myapplicationbottomnavigation.databinding.FragmentTaskBinding
import com.example.myapplicationbottomnavigation.ui.ext.Const

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: TaskModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTask.setOnClickListener {
            save()
        }
        checkTask()
    }

    private fun save() {
        val updatedTask = binding.etTask.text.toString()
        val isNewTask = task == null
        if (isNewTask) {
            returnNewTask(updatedTask)
        } else {
            returnExistingTask(updatedTask)
        }
    }

    private fun checkTask() {
        task = arguments?.getSerializable(Const.ARG_TASK) as? TaskModel
        if (task != null) {
            binding.etTask.setText(task!!.task)
        }
    }

    private fun returnNewTask(updatedTask: String) {
        val bundle = bundleOf(
            Const.KEY_FOR_TASK to TaskModel(updatedTask, System.currentTimeMillis())
        )
        setFragmentResult(Const.REQUEST_TASK_RESULT, bundle)
        findNavController().navigateUp()
    }

    private fun returnExistingTask(updatedTask: String) {
        val time = task?.time ?: return
        val bundle = bundleOf(
            Const.KEY_FOR_EXISTING_TASK to TaskModel(updatedTask, time)
        )
        setFragmentResult(Const.REQUEST_TASK_RESULT, bundle)
        findNavController().navigateUp()
    }

}
package com.example.myapplicationbottomnavigation.ui.task

import java.io.Serializable

data class TaskModel(
    val task: String,
    var time: Long
) : Serializable
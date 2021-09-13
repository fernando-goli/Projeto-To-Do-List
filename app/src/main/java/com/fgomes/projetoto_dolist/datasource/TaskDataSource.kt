package com.fgomes.projetoto_dolist.datasource

import androidx.lifecycle.LiveData
import com.fgomes.projetoto_dolist.data.Task
import com.fgomes.projetoto_dolist.data.TaskDAO
import com.fgomes.projetoto_dolist.data.TaskRepository

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task) {
        if (task.id == 0) {
            list.add(task.copy(id = list.size + 1))
        } else {
            list.remove(task)
            list.add(task)
        }
    }

    fun findById(taskId: Int) = list.find { it.id == taskId }

    fun deleteTask(task: Task) {
        list.remove(task)
    }
}
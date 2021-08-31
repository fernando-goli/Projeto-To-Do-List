package com.fgomes.projetoto_dolist.datasource

import com.fgomes.projetoto_dolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list

    fun insertTask(task: Task){
        list.add(task.copy(id = list.size + 1))
    }
}
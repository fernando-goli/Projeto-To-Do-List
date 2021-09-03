package com.fgomes.projetoto_dolist.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskRepository(private val dao: TaskDAO) {
    fun insert(task: Task) = runBlocking {
        launch (Dispatchers.IO){
            dao.insert(task)
        }
    }

    fun getAll() = dao.getAll()

    /*fun delete(task: Task) = dao.delete(task)

    fun update(task: Task) = dao.update(task)*/

}
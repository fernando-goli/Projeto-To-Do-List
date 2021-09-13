package com.fgomes.projetoto_dolist.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskRepository(private val dao: TaskDAO) {

    fun insertTask(task: Task) = runBlocking {
        launch (Dispatchers.IO){
            dao.insert(task)
        }
    }

    fun getAllTask() = dao.getAll()

    suspend fun deleteTask(id: Int) = dao.delete(id)

    /*fun updateTask(task: Task) = dao.update(task)*/

}
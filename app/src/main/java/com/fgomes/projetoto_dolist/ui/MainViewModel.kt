package com.fgomes.projetoto_dolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fgomes.projetoto_dolist.data.Task
import com.fgomes.projetoto_dolist.data.TaskRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun insertTask (task: Task) {
        taskRepository.insertTask(task)
    }

    fun getAllTask(): LiveData<List<Task>> = taskRepository.getAllTask()

    fun deleteTask(id: Int)  {
        viewModelScope.launch{
        taskRepository.deleteTask( id )
        }
    }

    /*fun update(task: Task){
        taskRepository.update(task)
    }*/

}
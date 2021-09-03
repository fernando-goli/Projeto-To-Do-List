package com.fgomes.projetoto_dolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fgomes.projetoto_dolist.data.Task
import com.fgomes.projetoto_dolist.data.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun insert (task: Task) {
        taskRepository.insert(task)
    }

    fun getAll(): LiveData<List<Task>> = taskRepository.getAll()

    /*fun delete(task: Task){
        taskRepository.delete(task)
    }

    fun update(task: Task){
        taskRepository.update(task)
    }*/

}

class MainViewModelFactory(private val repository: TaskRepository)
    : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}
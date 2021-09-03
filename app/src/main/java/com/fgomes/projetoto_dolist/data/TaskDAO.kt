package com.fgomes.projetoto_dolist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    //Exibe todas task's
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>

    //Delete single task
   /* @Query("SELECT * FROM TaskTable WHERE id = :id")
    fun delete (id: Task): LiveData<List<Task>>

    @Update
    fun update(task: Task)*/

}
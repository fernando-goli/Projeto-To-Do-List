package com.fgomes.projetoto_dolist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    //Exibe todas task's
    @Query("SELECT * FROM table_task")
    fun getAll(): LiveData<List<Task>>

    //Delete single task
    @Query("Delete FROM table_task WHERE id = :id")
    suspend fun delete (id: Int)

    @Update
    suspend fun update(task: Task)


}
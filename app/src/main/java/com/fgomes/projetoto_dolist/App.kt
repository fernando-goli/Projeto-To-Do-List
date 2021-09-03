package com.fgomes.projetoto_dolist

import android.app.Application
import com.fgomes.projetoto_dolist.data.TaskRepository
import com.fgomes.projetoto_dolist.data.TaskRoomDatabase

class App: Application() {
    val database by lazy { TaskRoomDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}
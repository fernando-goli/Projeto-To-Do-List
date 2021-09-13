package com.fgomes.projetoto_dolist

import android.app.Application
import com.fgomes.projetoto_dolist.data.TaskRepository
import com.fgomes.projetoto_dolist.data.AppDatabase

class App: Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}
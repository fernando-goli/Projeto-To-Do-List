package com.fgomes.projetoto_dolist

import android.app.Application
import com.fgomes.projetoto_dolist.di.repositoryModule
import com.fgomes.projetoto_dolist.di.roomModule
import com.fgomes.projetoto_dolist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

   /* val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }*/

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(viewModelModule)
            modules(roomModule)
            modules(repositoryModule)
        }
    }

}
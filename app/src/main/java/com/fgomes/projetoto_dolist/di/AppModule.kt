package com.fgomes.projetoto_dolist.di

import androidx.room.Room
import com.fgomes.projetoto_dolist.data.AppDatabase
import com.fgomes.projetoto_dolist.data.TaskDAO
import com.fgomes.projetoto_dolist.data.TaskRepository
import com.fgomes.projetoto_dolist.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val roomModule = module {

    single { Room.databaseBuilder(
        androidContext(), AppDatabase::class.java,"task_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun providerTaskDao(database: AppDatabase): TaskDAO = database.taskDao()
    single { providerTaskDao(get() ) }
    //single { get<AppDatabase>().taskDao() }

}

val repositoryModule = module {
    single { TaskRepository(get() ) }

}
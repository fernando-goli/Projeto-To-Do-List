package com.fgomes.projetoto_dolist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.fgomes.projetoto_dolist.App
import com.fgomes.projetoto_dolist.adapter.TaskListAdapter
import com.fgomes.projetoto_dolist.databinding.ActivityMainBinding
import com.fgomes.projetoto_dolist.datasource.TaskDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)  }

    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter
        getAllTask()
        insertListeners()

    }

    private fun insertListeners() {
        binding.fabTask.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,
                AddTaskActivity::class.java), CREATE_NEW_TASK)
        }
        //TODO
        //binding.rvTasks.setOnClickListener {  }
        //binding.rvTasks.addOnItemTouchListener()



        adapter.listenerEdit = {
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }

        adapter.listenerDelete = {
            //mainViewModel.delete(it)
            TaskDataSource.deleteTask(it)
            getAllTask()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) getAllTask()
    }

    private fun getAllTask() {
        mainViewModel.getAll().observe(this){ task ->
            binding.includeEmpty.emptyState.visibility = if (task.isEmpty()) View.VISIBLE
            else View.GONE
            adapter.submitList(task)
        }

        /*
        val list = TaskDataSource.getList()
        binding.includeEmpty.emptyState.visibility = if (list.isEmpty()) View.VISIBLE
        else View.GONE
        adapter.submitList(list)
        */
    }

    companion object {
        private const val CREATE_NEW_TASK = 1000
    }


}
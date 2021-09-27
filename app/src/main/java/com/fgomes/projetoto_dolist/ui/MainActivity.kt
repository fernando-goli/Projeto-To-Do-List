package com.fgomes.projetoto_dolist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fgomes.projetoto_dolist.adapter.TaskListAdapter
import com.fgomes.projetoto_dolist.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

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

        adapter.listenerEdit = { task ->
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, task.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }

        adapter.listenerDelete = {
            mainViewModel.deleteTask(it.id)
            getAllTask()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) getAllTask()
    }

    private fun getAllTask() {
        mainViewModel.getAllTask().observe(this){ task ->
            binding.includeEmpty.emptyState.visibility = if (task.isEmpty()) View.VISIBLE
            else View.GONE
            adapter.submitList(task)
        }

    }

    companion object {
        private const val CREATE_NEW_TASK = 1000
    }

}
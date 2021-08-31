package com.fgomes.projetoto_dolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fgomes.projetoto_dolist.databinding.ActivityAddTaskBinding
import com.fgomes.projetoto_dolist.datasource.TaskDataSource
import com.fgomes.projetoto_dolist.extensions.format
import com.fgomes.projetoto_dolist.extensions.text
import com.fgomes.projetoto_dolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                binding.tilDate.text = Date(it).format()
            }
            datePicker.show( supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tilHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                val minute = if(timePicker.minute in 0..9)"0${timePicker.minute}" else timePicker.minute
                val hour = if(timePicker.hour in 0..9)"0${timePicker.hour}" else timePicker.hour
                binding.tilHour.text = "${hour}:${minute}"
            }
            timePicker.show( supportFragmentManager, null)
        }

        binding.btNewTask.setOnClickListener {
            val task = Task(
                title = binding.tilTitle.text,
                date = binding.tilDate.text,
                hour = binding.tilHour.text
            )
            TaskDataSource.insertTask(task)
            Log.d("Tag","insertLiteners" + TaskDataSource.getList())
        }

        binding.btCancel.setOnClickListener {
            finish()
        }
    }




}
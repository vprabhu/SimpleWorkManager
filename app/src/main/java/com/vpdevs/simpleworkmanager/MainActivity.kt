package com.vpdevs.simpleworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.vpdevs.simpleworkmanager.databinding.ActivityMainBinding
import com.vpdevs.simpleworkmanager.workers.SimpleAddWorker

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // initialise the work manager
    private val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.progressBarAdd.visibility = View.GONE

        binding.buttonAdd.setOnClickListener {
            Log.d("SimpleAddWorker" , "Thread : ${Thread.currentThread().name}")
            // call the work manager to begin the task by using the OneTimeWorkRequest
            workManager.beginWith(
                OneTimeWorkRequest.from(SimpleAddWorker::class.java)
            ).enqueue()
        }
    }
}
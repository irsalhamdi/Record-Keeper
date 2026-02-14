package com.irsalhamdi.recordkeeper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.irsalhamdi.recordkeeper.databinding.ActivityEditCyclingBinding

class EditCyclingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCyclingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEditCyclingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("Category")

        title = "$category Cycling"
    }
}
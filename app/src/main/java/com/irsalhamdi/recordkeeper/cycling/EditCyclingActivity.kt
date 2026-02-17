package com.irsalhamdi.recordkeeper.cycling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irsalhamdi.recordkeeper.databinding.ActivityEditCyclingBinding

class EditCyclingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCyclingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditCyclingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra("Category")

        title = "$category Cycling"
    }
}
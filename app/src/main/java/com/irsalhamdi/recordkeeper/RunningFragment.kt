package com.irsalhamdi.recordkeeper

import android.os.Bundle
import android.view.View
import android.content.Intent
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.irsalhamdi.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment: Fragment() {

    private lateinit var binding: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListener()
    }

    private fun setupOnClickListener() {
        binding.container10km.setOnClickListener { launchRunningRecordScreen("10 KM") }
        binding.container5km.setOnClickListener { launchRunningRecordScreen("5 KM") }
        binding.container2km.setOnClickListener { launchRunningRecordScreen("2 KM") }
    }

    private fun launchRunningRecordScreen(distance: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)

        startActivity(intent)
    }
}
package com.irsalhamdi.recordkeeper.running

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.content.Context.MODE_PRIVATE
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

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences("running", MODE_PRIVATE)

        binding.textViewTime10km.text = runningPreferences.getString("10 KM record", null)
        binding.textViewDate10km.text = runningPreferences.getString("10 KM date", null)
        binding.textViewTime5km.text = runningPreferences.getString("5 KM record", null)
        binding.textViewDate5km.text = runningPreferences.getString("5 KM date", null)
        binding.textViewTime2km.text = runningPreferences.getString("2 KM record", null)
        binding.textViewDate2km.text = runningPreferences.getString("2 KM date", null)
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
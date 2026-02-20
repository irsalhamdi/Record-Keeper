package com.irsalhamdi.recordkeeper.cycling

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.content.Context.MODE_PRIVATE
import com.irsalhamdi.recordkeeper.editrecord.INTENT_EXTRA
import com.irsalhamdi.recordkeeper.editrecord.EditRecordActivity
import com.irsalhamdi.recordkeeper.databinding.FragmentCyclingBinding

class CyclingFragment: Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
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
        val cyclingPreferences = requireContext().getSharedPreferences(FILENAME, MODE_PRIVATE)

        binding.textViewLongestRide10km.text = cyclingPreferences.getString("$RECORD_LONGEST_RIDE ${EditRecordActivity.SHARED_PREFERENCED_RECORD_KEY}", null)
        binding.textViewLongestRideDate10km.text = cyclingPreferences.getString("$RECORD_LONGEST_RIDE ${EditRecordActivity.SHARED_PREFERENCED_DATE_KEY}", null)
        binding.textViewBiggestClimb5km.text = cyclingPreferences.getString("$RECORD_BIGGEST_CLIMB ${EditRecordActivity.SHARED_PREFERENCED_RECORD_KEY}", null)
        binding.textViewDateBiggestClimb5km.text = cyclingPreferences.getString("$RECORD_BIGGEST_CLIMB ${EditRecordActivity.SHARED_PREFERENCED_DATE_KEY}", null)
        binding.textViewBestSpeedTime2km.text = cyclingPreferences.getString("$RECORD_BEST_SPEED ${EditRecordActivity.SHARED_PREFERENCED_RECORD_KEY}", null)
        binding.textViewDate2km.text = cyclingPreferences.getString("$RECORD_BEST_SPEED ${EditRecordActivity.SHARED_PREFERENCED_DATE_KEY}", null)
    }

    private fun setupOnClickListener() {
        binding.containerLongestRide.setOnClickListener { launchCyclingRecordScreen(RECORD_LONGEST_RIDE, "Distance") }
        binding.containerBiggestClimb.setOnClickListener { launchCyclingRecordScreen(RECORD_BIGGEST_CLIMB, "Height") }
        binding.containerBestSpeed.setOnClickListener { launchCyclingRecordScreen(RECORD_BEST_SPEED, "Average Speed") }
    }

    private fun launchCyclingRecordScreen(category: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(
            INTENT_EXTRA, EditRecordActivity.ScreenRecord(
            record = category,
            sharedPreferencesName = FILENAME,
            recordFieldHint = recordFieldHint
        ))

        startActivity(intent)
    }

    companion object{
        const val FILENAME = "cycling"
        const val RECORD_LONGEST_RIDE = "Longest Ride"
        const val RECORD_BIGGEST_CLIMB = "Biggest Climb"
        const val RECORD_BEST_SPEED = "Best Average Speed"
    }
}
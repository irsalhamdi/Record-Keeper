package com.irsalhamdi.recordkeeper.editrecord

import android.os.Bundle
import android.view.MenuItem
import java.io.Serializable
import androidx.core.content.edit
import androidx.appcompat.app.AppCompatActivity
import com.irsalhamdi.recordkeeper.databinding.ActivityEditRecordBinding

const val INTENT_EXTRA = "screen_data"

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    private val screenData by lazy{ intent.getSerializableExtra(INTENT_EXTRA, ScreenRecord::class.java) as ScreenRecord }
    private val recordPreferences by lazy { getSharedPreferences(screenData.sharedPreferencesName, MODE_PRIVATE) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        displayRecord()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupUI() {
        title = "${screenData.record} Record"

        binding.editTextRecord.hint = screenData.recordFieldHint

        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonClear.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(recordPreferences.getString("${screenData.record} $SHARED_PREFERENCED_RECORD_KEY", null))
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} $SHARED_PREFERENCED_DATE_KEY", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        recordPreferences.edit {
            putString("${screenData.record} $SHARED_PREFERENCED_RECORD_KEY", record)
            putString("${screenData.record} $SHARED_PREFERENCED_DATE_KEY", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} $SHARED_PREFERENCED_RECORD_KEY")
            remove("${screenData.record} $SHARED_PREFERENCED_DATE_KEY")
        }
    }

    data class ScreenRecord(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : Serializable

    companion object{
        const val SHARED_PREFERENCED_RECORD_KEY = "record"
        const val SHARED_PREFERENCED_DATE_KEY = "date"
    }
}
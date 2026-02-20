package com.irsalhamdi.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.edit
import androidx.fragment.app.commit
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.irsalhamdi.recordkeeper.cycling.CyclingFragment
import com.irsalhamdi.recordkeeper.running.RunningFragment
import com.google.android.material.navigation.NavigationBarView
import com.irsalhamdi.recordkeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener{
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuClickHandle = when (item.itemId) {
            R.id.reset_running -> {

                showConfirmationDialog(RUNNING_DISPLAY_VALUE)
                true
            }

            R.id.reset_bike -> {
                showConfirmationDialog(CYCLING_DISPLAY_VALUE)
                true
            }

            R.id.reset_all -> {
                showConfirmationDialog(ALL_DISPLAY_VALUE)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

        return menuClickHandle
    }
    private fun showConfirmationDialog(selection: String) {
        AlertDialog.Builder(this)
            .setTitle("Reset $selection Record")
            .setMessage("Are you sure you want to delete all $selection records?")
            .setPositiveButton("Yes") { _, _ ->
                when (selection) {
                    ALL_DISPLAY_VALUE -> {
                        getSharedPreferences(RunningFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                        getSharedPreferences(CyclingFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                    }
                    RUNNING_DISPLAY_VALUE -> getSharedPreferences(RunningFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                    CYCLING_DISPLAY_VALUE -> getSharedPreferences(CyclingFragment.FILENAME, MODE_PRIVATE).edit { clear() }
                }

                refreshCurrentFragment()

                showConfirmation()
            }
            .setNegativeButton("No", null)
            .show()
    }
    private fun showConfirmation() {
        val snackbar = Snackbar.make(binding.root, "Records Deleted", Snackbar.LENGTH_LONG)

        snackbar.anchorView = binding.bottomNavigation
        snackbar.show()
    }
    private fun refreshCurrentFragment() {
        when (binding.bottomNavigation.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_bike -> onCyclingClicked()
            else -> {}
        }
    }

    fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_layout, RunningFragment())
        }

        return true
    }
    fun onCyclingClicked(): Boolean{
        supportFragmentManager.commit {
            replace(R.id.frame_layout, CyclingFragment())
        }

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem) =  when (item.itemId) {
        R.id.nav_running -> onRunningClicked()
        R.id.nav_bike -> onCyclingClicked()
        else -> false
    }

    companion object {
        const val RUNNING_DISPLAY_VALUE = "running"
        const val CYCLING_DISPLAY_VALUE = "cycling"
        const val ALL_DISPLAY_VALUE = "all"
    }
}

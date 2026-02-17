package com.irsalhamdi.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.commit
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.irsalhamdi.recordkeeper.cycling.CyclingFragment
import com.irsalhamdi.recordkeeper.databinding.ActivityMainBinding
import com.irsalhamdi.recordkeeper.running.RunningFragment

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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.reset_running -> {
            supportFragmentManager.commit {
                replace(R.id.frame_layout, RunningFragment())
            }
            true
        }

        R.id.reset_bike -> {
            supportFragmentManager.commit {
                replace(R.id.frame_layout, CyclingFragment())
            }
            true
        }

        else -> super.onOptionsItemSelected(item)
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
}

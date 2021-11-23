package com.example.newsagreggatorapp

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS
import android.provider.Settings.EXTRA_APP_PACKAGE
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar


/**
 * This class represent MainActivity. It handle bottom naviagationbar and toolbar
 *
 * @author Piotr Obara
 * 967793
 */
class MainActivity : AppCompatActivity() {

    val profileActivity = ProfileActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbarMenu = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbarMenu)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate((R.menu.toolbar_menu), menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myView = findViewById<View>(R.id.main_toolbar)

        when(item!!.itemId) {
            R.id.profile -> {

                if (profileActivity.getCurrentUser() == null) {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, LoggedInActivity::class.java)
                    startActivity(intent)
                }
                //val snackbar = Snackbar.make(myView, getString(R.string.profile), Snackbar.LENGTH_LONG)
                //snackbar.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
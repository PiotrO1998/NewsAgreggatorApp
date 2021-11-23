package com.example.newsagreggatorapp

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

/**
 * This class represent LoggedInActivity
 *
 * @author Piotr Obara
 * 967793
 */
class LoggedInActivity : AppCompatActivity () {

    var mAuth = FirebaseAuth.getInstance()

    var db: DB = DB()

    var sqliteDatabase: SqliteDatabase? = db.getInstance(this)
    var preferencesList: ArrayList<Preference>? = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logged_in_layout)

        val buttonLogOut = findViewById<Button>(R.id.buttonLogedOut)
        val buttonSavedArticles = findViewById<Button>(R.id.buttonSavedArticles)
        val switch: Switch = findViewById(R.id.switchNotyfication)
        val buttonNotyficationSettings = findViewById<Button>(R.id.buttonNotificationSetting)

        checkBoxes(sqliteDatabase!!)

        buttonLogOut.setOnClickListener { view ->
            message(view, "Logging out")
            singOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonSavedArticles.setOnClickListener {
            val intent = Intent(this, SavedArticlesActivity::class.java)
            startActivity(intent)
        }

        mAuth.addAuthStateListener {
            if (mAuth.currentUser == null) {
                this.finish()
            }
        }

        switch.setOnClickListener {
            if (switch.isChecked){
                startService()
            } else {
                stopService()
            }
        }

        buttonNotyficationSettings.setOnClickListener {
            goToNotificationSettings(NotificationHelper.CHANNEL_TWO_ID)
        }

    }

    private fun singOut() {
        mAuth.signOut()
    }

    private fun message(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }


    /**
     * This method checked boxes when user click on them and add/delete preference to database
     */
    fun onCheckboxClicked(view: View) {
        //val sqliteDatabase = SqliteDatabase(this)
        //val preferencesList = sqliteDatabase.listPreferences()

        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {

                R.id.checkbox_science -> {
                    if (checked) {
                        System.out.println("Adddddd")
                        sqliteDatabase?.addTask("science", mAuth.currentUser?.email!!)
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    } else if (!checked) {
                        System.out.println("Deleteeee")
                        sqliteDatabase?.deleteTask("science")
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    }
                }
                R.id.checkbox_general -> {
                    if (checked) {
                        System.out.println("Adddddddd")
                        sqliteDatabase?.addTask("general", mAuth.currentUser?.email!!)
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    } else if (!checked) {
                        System.out.println("Deleteeee")
                        sqliteDatabase?.deleteTask("general")
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    }
                }
                R.id.checkbox_entertainment -> {
                    if (checked) {
                        System.out.println("Adddddddd")
                        sqliteDatabase?.addTask("entertainment", mAuth.currentUser?.email!!)
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    } else if (!checked) {
                        System.out.println("Deleteeee")
                        sqliteDatabase?.deleteTask("entertainment")
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    }
                }
                R.id.checkbox_health -> {
                    if (checked) {
                        System.out.println("Adddddddd")
                        sqliteDatabase?.addTask("health", mAuth.currentUser?.email!!)
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    } else if (!checked) {
                        System.out.println("Deleteeee")
                        sqliteDatabase?.deleteTask("health")
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    }
                }
                R.id.checkbox_business -> {
                    if (checked) {
                        System.out.println("Adddddddd")
                        sqliteDatabase?.addTask("business", mAuth.currentUser?.email!!)
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    } else if (!checked) {
                        System.out.println("Deleteeee")
                        sqliteDatabase?.deleteTask("business")
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    }
                }
                R.id.checkbox_technology -> {
                    if (checked) {
                        System.out.println("Adddddddd")
                        sqliteDatabase?.addTask("technology", mAuth.currentUser?.email!!)
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    } else if (!checked) {
                        System.out.println("Deleteeee")
                        sqliteDatabase?.deleteTask("technology")
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    }
                }
                R.id.checkbox_sport -> {
                    if (checked) {
                        System.out.println("Adddddddd")
                        sqliteDatabase?.addTask("sport", mAuth.currentUser?.email!!)
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    } else if (!checked) {
                        System.out.println("Deleteeee")
                        sqliteDatabase?.deleteTask("sport")
                        preferencesList?.clear()
                        preferencesList = sqliteDatabase?.listPreferences()
                        getDatabase(sqliteDatabase!!)
                    }
                }
            }
        }
    }

    fun getDatabase(sqliteDatabase: SqliteDatabase) {
        var preferencesList = sqliteDatabase.listPreferences()
        for (preference in preferencesList) {
            println("tes" + preference.name + preference.user)

        }
    }

    /**
     * This method check boxes when user login in
     */
    fun checkBoxes(sqliteDatabase: SqliteDatabase) {
        var preferencesList = sqliteDatabase.listPreferences()
        for (preference in preferencesList) {
            if (preference.name == "science" && mAuth.currentUser?.email == preference.user) {
                (findViewById<View>(R.id.checkbox_science) as Checkable).isChecked = true
            } else if (preference.name == "general" && mAuth.currentUser?.email == preference.user) {
                (findViewById<View>(R.id.checkbox_general) as Checkable).isChecked = true
            } else if (preference.name == "entertainment" && mAuth.currentUser?.email == preference.user) {
                (findViewById<View>(R.id.checkbox_entertainment) as Checkable).isChecked = true
            } else if (preference.name == "health" && mAuth.currentUser?.email == preference.user) {
                (findViewById<View>(R.id.checkbox_health) as Checkable).isChecked = true
            } else if (preference.name == "business" && mAuth.currentUser?.email == preference.user) {
                (findViewById<View>(R.id.checkbox_business) as Checkable).isChecked = true
            } else if (preference.name == "technology" && mAuth.currentUser?.email == preference.user) {
                (findViewById<View>(R.id.checkbox_technology) as Checkable).isChecked = true
            } else if (preference.name == "sport" && mAuth.currentUser?.email == preference.user) {
                (findViewById<View>(R.id.checkbox_sport) as Checkable).isChecked = true
            }

        }

    }

    fun startService(){
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

    fun stopService(){
        val serviceIntent = Intent(this, MyService::class.java)
        stopService(serviceIntent)
    }

    fun goToNotificationSettings(channel: String) {
        val i = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
        i.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        i.putExtra(Settings.EXTRA_CHANNEL_ID, channel)
        startActivity(i)
    }

}
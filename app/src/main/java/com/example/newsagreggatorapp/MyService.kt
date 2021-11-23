package com.example.newsagreggatorapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.os.IBinder
import android.provider.Settings
import android.view.View
import androidx.core.app.NotificationCompat
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.timerTask

/**
 * This class represnt Service that will run in background and send notyfication
 *
 * @author Piotr Obara
 * 967796
 */
class MyService : Service() {

    val profileActivity = ProfileActivity()
    private var notificationHelper: NotificationHelper? = null
    var t = Timer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        notificationHelper = NotificationHelper(this)
        reapetNotyfication()
    }

    override fun onDestroy() {
        super.onDestroy()
        t.cancel()
    }

    fun postNotification() {
        var notificationBuilder: NotificationCompat.Builder? = null
        notificationBuilder = notificationHelper!!.getNotification2()

        if (notificationBuilder != null) {
            notificationHelper!!.notify(notificationBuilder)
        }
    }

    /**
     * Sending daily notyfication
     */
    fun reapetNotyfication(){
        var d = Date()
        d.hours = 12
        d.time = 15

        t.scheduleAtFixedRate(timerTask { postNotification() }, d, 86400000)
    }

}
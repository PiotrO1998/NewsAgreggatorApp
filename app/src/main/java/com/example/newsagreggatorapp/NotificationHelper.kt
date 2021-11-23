package com.example.newsagreggatorapp

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import android.app.Notification
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color

/**
 * This clas is represent for creating and posting notyfication. It also open aplication when user
 * click on notyfication.
 *
 * @author Piotr Obara
 * 967793
 */
internal class NotificationHelper (base: Context) : ContextWrapper(base) {

    private var notifManager: NotificationManager? = null

    val intent = Intent(this, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    //Send your notifications to the NotificationManager system service//
        private val manager: NotificationManager?
        get() {
            if (notifManager == null) {
                notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            return notifManager
        }

        init {
            createChannels()
        }

    fun createChannels() {

        val notificationChannel2 =
                NotificationChannel(CHANNEL_TWO_ID, CHANNEL_TWO_NAME, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel2.enableLights(false)
        notificationChannel2.enableVibration(true)
        notificationChannel2.lightColor = Color.RED
        notificationChannel2.setShowBadge(false)
        notificationChannel2.lockscreenVisibility  = Notification.VISIBILITY_PUBLIC
        manager!!.createNotificationChannel(notificationChannel2)
    }

    //Create the notification that’ll be posted to Channel Two//
    fun getNotification2(): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, CHANNEL_TWO_ID)
                .setContentTitle("News")
                .setContentText("Don't miss any new articles")
                .setSmallIcon(R.drawable.ic_notyfication)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
    }

    fun notify(notification: NotificationCompat.Builder) {
        manager!!.notify(1 ,notification.build())
    }

    companion object {
        const val CHANNEL_TWO_ID = "com.example.tomowen.notificationsexample.TWO"
        const val CHANNEL_TWO_NAME = "News notyfication"
    }

}
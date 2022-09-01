package kirillkitten.schedulednotifications.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import kirillkitten.schedulednotifications.R

private const val CHANNEL_ID = "kirillkitten.schedulednotifications.DEFAULT_CHANNEL_ID"

fun getOpenUrlIntent(id: Int, context: Context): PendingIntent {
    val url = when (id) {
        1 -> "https://www.google.com/"
        2 -> "https://wikipedia.org/"
        3 -> "https://play.google.com/store/apps/details?id=org.telegram.messenger&hl=en&gl=US"
        else -> throw IllegalArgumentException("Invalid alarm id")
    }
    val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
    return PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )
}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Default Notification Channel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        context.getSystemService<NotificationManager>()?.run {
            createNotificationChannel(channel)
        }
    }
}

fun showNotification(id: Int, context: Context) {
    val text = when (id) {
        1 -> "Google"
        2 -> "Wikipedia"
        3 -> "Telegram"
        else -> throw IllegalArgumentException("Invalid alarm id")
    }
    val notification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle(id.toString())
        .setContentText(text)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setContentIntent(getOpenUrlIntent(id, context))
        .setAutoCancel(true)
        .build()
    NotificationManagerCompat.from(context).notify(id, notification)
}
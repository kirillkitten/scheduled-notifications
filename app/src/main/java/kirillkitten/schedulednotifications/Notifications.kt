package kirillkitten.schedulednotifications

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

private const val CHANNEL_ID = "kirillkitten.schedulednotifications.DEFAULT_CHANNEL_ID"

fun getOpenUrlIntent(context: Context): PendingIntent {
    val url = "https://ru.wikipedia.org/"
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

fun showNotification(context: Context) {
    val id = 1
    val notification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("1")
        .setContentText("Wikipedia")
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setContentIntent(getOpenUrlIntent(context))
        .setAutoCancel(true)
        .build()
    NotificationManagerCompat.from(context).notify(id, notification)
}
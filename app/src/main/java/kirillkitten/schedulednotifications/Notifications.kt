package kirillkitten.schedulednotifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

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
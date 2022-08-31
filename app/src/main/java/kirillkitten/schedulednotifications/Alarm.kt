package kirillkitten.schedulednotifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.core.content.getSystemService

fun runAlarm(context: Context, notifyInSeconds: Int) {
    val alarmIntent = Intent(context, NotificationBroadcastReceiver::class.java)
        .let { intent ->
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }
    context.getSystemService<AlarmManager>()?.set(
        AlarmManager.ELAPSED_REALTIME_WAKEUP,
        SystemClock.elapsedRealtime() + notifyInSeconds * 1000,
        alarmIntent
    )
}

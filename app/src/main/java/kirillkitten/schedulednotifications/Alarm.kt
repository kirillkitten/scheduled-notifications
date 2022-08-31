package kirillkitten.schedulednotifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.getSystemService

fun runAlarm(context: Context) {
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
        AlarmManager.RTC_WAKEUP,
        getAlarmTime(context),
        alarmIntent
    )
}

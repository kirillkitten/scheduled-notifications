package kirillkitten.schedulednotifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.getSystemService
import timber.log.Timber

const val ALARM_ID_KEY = "ALARM_ID"

fun runAlarms(context: Context) {
    for (id in 1..3) {
        if (getAlarmTime(id, context) != 0L) runAlarm(id, context)
    }
}

fun runAlarm(id: Int, context: Context) {
    Timber.i("runAlarm is invoked")
    val alarmIntent = Intent(context, NotificationBroadcastReceiver::class.java)
        .setFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        .apply {
            putExtra(ALARM_ID_KEY, id)
        }
    val alarmPendingIntent = PendingIntent.getBroadcast(
        context,
        id,
        alarmIntent,
        PendingIntent.FLAG_IMMUTABLE
    )
    context.getSystemService<AlarmManager>()?.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        getAlarmTime(id, context),
        alarmPendingIntent
    )
}

package kirillkitten.schedulednotifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.getSystemService
import timber.log.Timber

const val ALARM_ID_KEY = "ALARM_ID"

fun runAlarm(context: Context) {
    for (id in 1..3) {
        if (getAlarmTime(id, context) != 0L) runAlarm(id, context)
    }
}

fun runAlarm(id: Int, context: Context) {
    Timber.i("runAlarm is invoked")
    context.getSystemService<AlarmManager>()?.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        getAlarmTime(id, context),
        getAlarmIntent(id, context)
    )
}

fun cancelAlarm(context: Context) {
    for (id in 1..3) {
        cancelAlarm(id, context)
    }
}

fun cancelAlarm(id: Int, context: Context) {
    val sharedPref = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putLong(getAlarmKey(id), 0)
        apply()
    }
    context.getSystemService<AlarmManager>()?.cancel(getAlarmIntent(id, context))
}

private fun getAlarmIntent(
    id: Int,
    context: Context
): PendingIntent {
    val alarmIntent = Intent(context, NotificationBroadcastReceiver::class.java)
        .setFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        .apply {
            putExtra(ALARM_ID_KEY, id)
        }
    return PendingIntent.getBroadcast(
        context,
        id,
        alarmIntent,
        PendingIntent.FLAG_IMMUTABLE
    )
}


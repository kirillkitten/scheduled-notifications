package kirillkitten.schedulednotifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.getSystemService

fun runAlarm(context: Context) {
    val alarmIntent = Intent(context, NotificationBroadcastReceiver::class.java)
        .setFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        .let { intent ->
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
//    context.getSystemService<AlarmManager>()?.setExactAndAllowWhileIdle(
//        AlarmManager.RTC_WAKEUP,
//        getAlarmTime(context),
//        alarmIntent
//    )
    context.getSystemService<AlarmManager>()?.setAlarmClock(AlarmManager.AlarmClockInfo(getAlarmTime(context), alarmIntent), alarmIntent)
}

package kirillkitten.schedulednotifications

import android.content.Context

private const val PREFERENCES_KEY = "PREFERENCES"
private const val ALARM_KEY = "ALARM_1"

fun saveAlarm(context: Context, notifyInSeconds: Int) {
    val sharedPref = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putLong(
            ALARM_KEY,
            System.currentTimeMillis() + notifyInSeconds * 1000
        )
        apply()
    }
}

fun getAlarmTime(context: Context): Long {
    val sharedPref = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    return sharedPref.getLong(ALARM_KEY, 0)
}
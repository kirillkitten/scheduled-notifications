package kirillkitten.schedulednotifications

import android.content.Context
import timber.log.Timber

private const val PREFERENCES_KEY = "PREFERENCES"
private const val ALARM_1_KEY = "ALARM_1"
private const val ALARM_2_KEY = "ALARM_2"
private const val ALARM_3_KEY = "ALARM_3"

fun getAlarmKey(id: Int): String = when(id) {
    1 -> ALARM_1_KEY
    2 -> ALARM_2_KEY
    3 -> ALARM_3_KEY
    else -> throw IllegalArgumentException("Invalid alarm id")
}

fun saveAlarm(id: Int, context: Context, notifyInSeconds: Int) {
    Timber.i("saveAlarm function is invoked")
    val sharedPref = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putLong(
            getAlarmKey(id),
            System.currentTimeMillis() + notifyInSeconds * 1000
        )
        apply()
    }
}

fun getAlarmTime(id: Int, context: Context): Long {
    val sharedPref = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    return sharedPref.getLong(getAlarmKey(id), 0)
}

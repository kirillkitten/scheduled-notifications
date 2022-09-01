package kirillkitten.schedulednotifications.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kirillkitten.schedulednotifications.utils.runAlarm
import timber.log.Timber

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Timber.i("onReceive function is invoked")
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            runAlarm(context)
        }
    }
}
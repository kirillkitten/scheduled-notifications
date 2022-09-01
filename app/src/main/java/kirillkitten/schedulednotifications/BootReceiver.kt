package kirillkitten.schedulednotifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Timber.i("onReceive function is invoked")
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            runAlarm(context)
        }
    }
}
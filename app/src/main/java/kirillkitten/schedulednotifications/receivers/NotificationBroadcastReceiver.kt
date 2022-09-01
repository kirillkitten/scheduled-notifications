package kirillkitten.schedulednotifications.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kirillkitten.schedulednotifications.utils.ALARM_ID_KEY
import kirillkitten.schedulednotifications.utils.showNotification
import timber.log.Timber

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Timber.i("onReceive function is invoked")
        val id = intent.getIntExtra(ALARM_ID_KEY, 0)
        showNotification(id, context)
    }
}

package kirillkitten.schedulednotifications

import android.app.Application
import timber.log.Timber

class NotificationSchedulerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
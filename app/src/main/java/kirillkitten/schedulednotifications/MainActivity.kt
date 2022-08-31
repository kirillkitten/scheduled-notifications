package kirillkitten.schedulednotifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import kirillkitten.schedulednotifications.ui.theme.ScheduledNotificationsTheme

const val CHANNEL_ID = "kirillkitten.schedulednotifications.DEFAULT_CHANNEL_ID"

class MainActivity : ComponentActivity() {

    private val openUrlIntent by lazy {
        val url = "https://ru.wikipedia.org/"
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
        PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ScheduledNotificationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        createNotificationChannel()
        configureAlarm()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Default Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            getSystemService<NotificationManager>()?.run {
                createNotificationChannel(channel)
            }
        }
    }

    private fun showNotification() {
        val id = 1
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("1")
            .setContentText("Wikipedia")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(openUrlIntent)
            .setAutoCancel(true)
            .build()
        NotificationManagerCompat.from(this).notify(id, notification)
    }

    private fun configureAlarm() {
        val alarmIntent = Intent(this, NotificationBroadcastReceiver::class.java)
            .let { intent ->
                PendingIntent.getBroadcast(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }
        getSystemService<AlarmManager>()?.set(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 10 * 1000,
            alarmIntent
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScheduledNotificationsTheme {
        Greeting("Android")
    }
}
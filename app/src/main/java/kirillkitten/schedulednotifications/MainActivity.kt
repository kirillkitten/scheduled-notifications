package kirillkitten.schedulednotifications

import android.Manifest
import android.app.AlarmManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import kirillkitten.schedulednotifications.ui.theme.ScheduledNotificationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(this)
        grantScheduleAlarmPermission()
    }

    private fun configureScreen() {
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

        saveAlarm(this, 90)
        runAlarm(this)
    }

    private fun grantScheduleAlarmPermission() {
        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            && getSystemService<AlarmManager>()?.canScheduleExactAlarms() != true
        ) {
            val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts
                .RequestPermission()) { isGranted ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    configureScreen()
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }

            when (ContextCompat.checkSelfPermission(
                this, Manifest.permission.SCHEDULE_EXACT_ALARM)
                    == PackageManager.PERMISSION_GRANTED) {
                shouldShowRequestPermissionRationale(
                    Manifest.permission.SCHEDULE_EXACT_ALARM
                ) -> {
                    TODO()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.SCHEDULE_EXACT_ALARM)
                }
            }
        } else {
            configureScreen()
        }
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
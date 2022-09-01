package kirillkitten.schedulednotifications

import android.Manifest
import android.app.AlarmManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import kirillkitten.schedulednotifications.ui.theme.ScheduledNotificationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(this)
        grantScheduleAlarmPermission()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun configureScreen() {

        setContent {
            ScheduledNotificationsTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    var text1 by remember { mutableStateOf("") }
                    var text2 by remember { mutableStateOf("") }
                    var text3 by remember { mutableStateOf("") }
                    Row(
                        modifier = Modifier.padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Show Google in")
                        OutlinedTextField(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .width(64.dp),
                            value = text1,
                            onValueChange = { text1 = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Text("seconds")
                    }
                    Row(
                        modifier = Modifier.padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Show Wikipedia in")
                        OutlinedTextField(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .width(64.dp),
                            value = text2,
                            onValueChange = { text2 = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Text("seconds")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Show Telegram in")
                        OutlinedTextField(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .width(64.dp),
                            value = text3,
                            onValueChange = { text3 = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Text("seconds")
                    }
                    Row(modifier = Modifier.padding(top = 24.dp),) {
                        Button(
                            onClick = {
                                if (text1.isNotEmpty()) {
                                    saveAlarm(1, this@MainActivity, text1.toInt())
                                } else {
                                    cancelAlarm(1, this@MainActivity)
                                }
                                if (text2.isNotEmpty()) {
                                    saveAlarm(2, this@MainActivity, text2.toInt())
                                } else {
                                    cancelAlarm(2, this@MainActivity)
                                }
                                if (text3.isNotEmpty()) {
                                    saveAlarm(3, this@MainActivity, text3.toInt())
                                } else {
                                    cancelAlarm(3, this@MainActivity)
                                }
                                runAlarm(this@MainActivity)
                            }
                        ) {
                            Text(text = "Save")
                        }
                        Button(
                            modifier = Modifier.padding(start = 16.dp),
                            onClick = {
                                text1 = ""
                                text2 = ""
                                text3 = ""
                                cancelAlarm(this@MainActivity)
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }
    }

    private fun grantScheduleAlarmPermission() {
        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            && getSystemService<AlarmManager>()?.canScheduleExactAlarms() != true
        ) {
            val requestPermissionLauncher = registerForActivityResult(
                ActivityResultContracts
                    .RequestPermission()
            ) { isGranted ->
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
                this, Manifest.permission.SCHEDULE_EXACT_ALARM
            )
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
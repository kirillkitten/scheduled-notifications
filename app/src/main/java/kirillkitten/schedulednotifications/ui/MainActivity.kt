package kirillkitten.schedulednotifications.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kirillkitten.schedulednotifications.ui.theme.ScheduledNotificationsTheme
import kirillkitten.schedulednotifications.utils.cancelAlarm
import kirillkitten.schedulednotifications.utils.createNotificationChannel
import kirillkitten.schedulednotifications.utils.runAlarm
import kirillkitten.schedulednotifications.utils.saveAlarm

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(this)
        setContent {
            ScheduledNotificationsTheme {
                MainScreen()
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            var text1 by remember { mutableStateOf("") }
            var text2 by remember { mutableStateOf("") }
            var text3 by remember { mutableStateOf("") }
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Show Google in",
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(64.dp),
                    value = text1,
                    onValueChange = { text1 = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(
                    text = "seconds",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Show Wikipedia in",
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(64.dp),
                    value = text2,
                    onValueChange = { text2 = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(
                    text = "seconds",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Show Telegram in",
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(64.dp),
                    value = text3,
                    onValueChange = { text3 = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(
                    text = "seconds",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(modifier = Modifier.padding(top = 24.dp)) {
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

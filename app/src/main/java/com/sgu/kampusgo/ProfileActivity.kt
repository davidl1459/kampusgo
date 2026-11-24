package com.sgu.kampusgo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sgu.kampusgo.ui.theme.KampusGoTheme

class ProfileActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// First callback. The screen is being created. Write a line to Logcat.
        Log.d("KampusGo", "onCreate")
// Keep the Activity for the button. Inside setContent, "this" is not the Activity.
        val activity = this
// Read the extra named "name". If screen 1 sent nothing, use Guest. No !!.
        val name = intent.getStringExtra("name") ?: "Guest"
        val npm = intent.getStringExtra("npm")?.ifBlank {"-"} ?: "-"

        setContent {
            KampusGoTheme {
                Column(modifier = Modifier.padding(24.dp)) {
// Show the name that traveled inside the Intent.
                    Text(text = "Hello, $name")
                    Text(text = "NPM: $npm")
                    Button(onClick = {
// Implicit Intent: do not name an Activity. Ask the phone for a dialer.
                        val web = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sgu.ac.id"))
                        try {
                            activity.startActivity(web)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(activity, "No browser on this device", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text("Open SGU Website")
                    }
                }
            }
        }
    }

    // Visible, but not yet the screen in front.
    override fun onStart() {
        super.onStart()
        Log.d("KampusGo", "onStart")
    }
    // In front. The user can tap the button.
    override fun onResume() {
        super.onResume()
        Log.d("KampusGo", "onResume")
    }
    // Leaving, or the phone is rotating.
    override fun onPause() {
        super.onPause()
        Log.d("KampusGo", "onPause")
    }
    // No longer visible.
    override fun onStop() {
        super.onStop()
        Log.d("KampusGo", "onStop")
    }

    

}
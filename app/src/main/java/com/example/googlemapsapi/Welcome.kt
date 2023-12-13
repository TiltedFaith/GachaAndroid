package com.example.googlemapsapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val startBtn : Button = findViewById(R.id.start)

        startBtn.setOnClickListener{
            redirection()
        }
    }

    private fun redirection() {
        try {
            val intent = Intent(this, PullScreen::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("error_redirect", e.message.toString())

        }
    }

}
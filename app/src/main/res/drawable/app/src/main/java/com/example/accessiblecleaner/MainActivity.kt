package com.example.accessiblecleaner

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToggleService = findViewById<Button>(R.id.btnToggleService)
        val btnCleanJunk = findViewById<Button>(R.id.btnCleanJunk)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        btnToggleService.setOnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }

        btnCleanJunk.setOnClickListener {
            val cleaned = JunkCleanerUtils.cleanJunkFiles(this)
            if (cleaned) {
                tvStatus.text = "Status: Junk files cleaned successfully!"
            } else {
                tvStatus.text = "Status: Cleaning completed or permission needed."
            }
        }
    }
}

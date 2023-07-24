package com.example.canteeniitj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class admin_category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_category)

        findViewById<Button>(R.id.avail).setOnClickListener {
            startActivity(Intent(this,admin_available::class.java))
        }
        findViewById<Button>(R.id.unavail).setOnClickListener {
            startActivity(Intent(this,admin_unavailable::class.java))
        }
        findViewById<Button>(R.id.orders).setOnClickListener {
            startActivity(Intent(this,admin_orders::class.java))
        }
        findViewById<Button>(R.id.show_old_orders).setOnClickListener {
            startActivity(Intent(this,show_old_orders::class.java))
        }
    }
}
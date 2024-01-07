package com.example.canteeniitj

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Handler().postDelayed({
            var token1=getSharedPreferences("demo",Context.MODE_PRIVATE)
            var token=getSharedPreferences("status",Context.MODE_PRIVATE)
            if(token1.getString("user"," ")=="0"){
                startActivity(Intent(this,Sign_in::class.java))
                finish()
            }
            else if(token1.getString("user"," ")=="1"){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this,Sign_in::class.java))
                finish()
            }

        },1200)
    }
}
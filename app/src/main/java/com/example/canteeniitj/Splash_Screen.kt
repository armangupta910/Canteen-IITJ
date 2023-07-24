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
            var token=getSharedPreferences("status",Context.MODE_PRIVATE)
            if(token.getBoolean("status",true)==true){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this,Sign_in::class.java))
                val editor=token.edit()
                editor.putBoolean("status",false)
                editor.commit()
                finish();
            }

        },1800)
    }
}
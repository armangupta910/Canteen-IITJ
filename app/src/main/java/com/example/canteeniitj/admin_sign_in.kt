package com.example.canteeniitj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class admin_sign_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_sign_in)
        val id=findViewById<EditText>(R.id.adminid)
        val pass=findViewById<EditText>(R.id.adminpass)
        val butt=findViewById<Button>(R.id.signin)

        butt.setOnClickListener {
            if(id.text.toString()=="abc"){
                if(pass.text.toString()=="123"){
                    startActivity(Intent(this,admin_category::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this,"Password in wrong",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"ID is wrong",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
package com.example.canteeniitj

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Sign_in : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()
        firebaseAuth= FirebaseAuth.getInstance()
        findViewById<TextView>(R.id.signintosignuptext).setOnClickListener {
            startActivity(Intent(this,Sign_Up::class.java))
            finish()
        }
        findViewById<Button>(R.id.adminlogin123).setOnClickListener {
            startActivity(Intent(this,admin_sign_in::class.java))
        }
        val button:Button=findViewById(R.id.signinbutton)
        val progi:ProgressBar=findViewById(R.id.progi)

        var token=getSharedPreferences("demo", Context.MODE_PRIVATE)
        button.setOnClickListener {
            button.visibility=View.GONE
            progi.visibility=View.VISIBLE
            val email=findViewById<EditText>(R.id.rollsignin).text.toString()
            val pass=findViewById<EditText>(R.id.passsignin).text.toString()
            if(email=="" || pass==""){
                Toast.makeText(this,"Empty Fields",Toast.LENGTH_SHORT).show()
                button.visibility=View.VISIBLE
                progi.visibility=View.GONE
            }
            else{
                firebaseAuth.signInWithEmailAndPassword(email+"@iitj.ac.in",pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Sign in Successfull",Toast.LENGTH_SHORT).show()
                        var editor=token.edit()
                        editor.putString("user","1")
                        editor.commit()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else{
                        button.visibility=View.VISIBLE
                        progi.visibility=View.GONE
                        Toast.makeText(this,"Wrong credentials or Account does not exist",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}
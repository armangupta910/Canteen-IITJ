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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Sign_Up : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        var token=getSharedPreferences("status",Context.MODE_PRIVATE)
        firebaseAuth= FirebaseAuth.getInstance()
        findViewById<TextView>(R.id.signuptosignintext).setOnClickListener {
            startActivity(Intent(this,Sign_in::class.java))
            finish()
        }

        val button:Button=findViewById(R.id.signupbutton)
        val progi:ProgressBar=findViewById(R.id.progi1)
        button.setOnClickListener {
            button.visibility=View.GONE
            progi.visibility=View.VISIBLE
            val email=findViewById<EditText>(R.id.rollsignup).text.toString()
            val name=findViewById<EditText>(R.id.namesignup).text.toString()
            val hostel=findViewById<EditText>(R.id.hostelsignup).text.toString()
            val pass=findViewById<EditText>(R.id.passsignup).text.toString()
            val phone=findViewById<EditText>(R.id.phonesignup).text.toString()

            val x=(email=="" || name=="" || hostel=="" || pass=="" || phone=="")
            if(x){
                Toast.makeText(this,"Empty Fields",Toast.LENGTH_SHORT).show()
                button.visibility=View.VISIBLE
                progi.visibility=View.GONE
            }
            else{
                firebaseAuth.createUserWithEmailAndPassword(email+"@iitj.ac.in",pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Account Created",Toast.LENGTH_SHORT).show()
                        var editor=token.edit()
                        val db=Firebase.firestore
                        editor.putBoolean("status",true)
                        editor.commit()
                        val carti:MutableList<Int> = mutableListOf()
                        val cart:HashMap<String,Int> = hashMapOf(
                            "1" to 0,
                            "2" to 0,
                            "3" to 0,
                            "4" to 0,
                            "5" to 0,
                            "6" to 0,
                            "7" to 0,
                            "8" to 0,
                            "9" to 0,
                            "10" to 0,
                            "11" to 0,
                            "12" to 0,
                            "13" to 0,
                            "14" to 0,
                            "15" to 0,
                            "16" to 0,
                            "17" to 0,
                            "18" to 0,
                            "19" to 0,
                            "20" to 0,
                            "21" to 0,
                            "22" to 0,
                            "23" to 0,
                            "24" to 0,
                            "25" to 0,
                            "26" to 0,
                            "27" to 0,
                            "28" to 0,
                            "29" to 0,
                            "30" to 0,
                            "31" to 0,
                            "32" to 0,
                            "33" to 0,
                            "34" to 0,
                            "35" to 0,
                            "36" to 0,
                            "37" to 0,
                            "38" to 0,
                            "39" to 0,
                            "40" to 0,
                            "41" to 0,
                            "42" to 0,
                            "43" to 0,
                            "44" to 0,
                            "45" to 0,
                            "46" to 0,
                            "47" to 0,
                            "48" to 0,
                            "49" to 0,
                            "50" to 0,
                            "51" to 0,
                            "52" to 0,
                            "53" to 0,
                            "54" to 0,
                            "55" to 0,
                            "56" to 0,
                            "57" to 0,
                            "58" to 0,
                            "59" to 0,
                            "60" to 0,
                            "61" to 0,
                            "62" to 0,
                            "63" to 0,
                            "64" to 0,
                            "65" to 0,
                            "66" to 0,
                            "67" to 0,
                            "68" to 0,
                            "69" to 0,
                            "70" to 0,
                            "71" to 0,
                            "72" to 0,
                            "73" to 0,
                            "74" to 0,
                            "75" to 0,
                            "76" to 0,
                            "77" to 0,
                            "78" to 0,
                            "79" to 0,
                            "80" to 0,
                            "81" to 0,
                            "82" to 0,
                            "83" to 0,
                            "84" to 0,
                            "85" to 0,
                            "86" to 0,
                            "87" to 0,
                            "88" to 0,
                            "89" to 0,
                            "90" to 0,
                            "91" to 0,
                            "92" to 0,
                            "93" to 0,
                            "94" to 0,
                            "95" to 0,
                            "96" to 0,
                            "97" to 0,
                            "98" to 0,
                            "99" to 0,
                            "100" to 0,
                            "101" to 0,
                            "102" to 0,
                            "103" to 0,
                            "104" to 0,
                            "105" to 0,
                            "106" to 0

                        )
                        val fav:HashMap<String,Int> = hashMapOf(
                            "1" to 0,
                            "2" to 0,
                            "3" to 0,
                            "4" to 0,
                            "5" to 0,
                            "6" to 0,
                            "7" to 0,
                            "8" to 0,
                            "9" to 0,
                            "10" to 0,
                            "11" to 0,
                            "12" to 0,
                            "13" to 0,
                            "14" to 0,
                            "15" to 0,
                            "16" to 0,
                            "17" to 0,
                            "18" to 0,
                            "19" to 0,
                            "20" to 0,
                            "21" to 0,
                            "22" to 0,
                            "23" to 0,
                            "24" to 0,
                            "25" to 0,
                            "26" to 0,
                            "27" to 0,
                            "28" to 0,
                            "29" to 0,
                            "30" to 0,
                            "31" to 0,
                            "32" to 0,
                            "33" to 0,
                            "34" to 0,
                            "35" to 0,
                            "36" to 0,
                            "37" to 0,
                            "38" to 0,
                            "39" to 0,
                            "40" to 0,
                            "41" to 0,
                            "42" to 0,
                            "43" to 0,
                            "44" to 0,
                            "45" to 0,
                            "46" to 0,
                            "47" to 0,
                            "48" to 0,
                            "49" to 0,
                            "50" to 0,
                            "51" to 0,
                            "52" to 0,
                            "53" to 0,
                            "54" to 0,
                            "55" to 0,
                            "56" to 0,
                            "57" to 0,
                            "58" to 0,
                            "59" to 0,
                            "60" to 0,
                            "61" to 0,
                            "62" to 0,
                            "63" to 0,
                            "64" to 0,
                            "65" to 0,
                            "66" to 0,
                            "67" to 0,
                            "68" to 0,
                            "69" to 0,
                            "70" to 0,
                            "71" to 0,
                            "72" to 0,
                            "73" to 0,
                            "74" to 0,
                            "75" to 0,
                            "76" to 0,
                            "77" to 0,
                            "78" to 0,
                            "79" to 0,
                            "80" to 0,
                            "81" to 0,
                            "82" to 0,
                            "83" to 0,
                            "84" to 0,
                            "85" to 0,
                            "86" to 0,
                            "87" to 0,
                            "88" to 0,
                            "89" to 0,
                            "90" to 0,
                            "91" to 0,
                            "92" to 0,
                            "93" to 0,
                            "94" to 0,
                            "95" to 0,
                            "96" to 0,
                            "97" to 0,
                            "98" to 0,
                            "99" to 0,
                            "100" to 0,
                            "101" to 0,
                            "102" to 0,
                            "103" to 0,
                            "104" to 0,
                            "105" to 0,
                            "106" to 0
                        )
                        val favi:MutableList<Int> = mutableListOf()
                        val orders:HashMap<String,HashMap<String,Int>> = hashMapOf()
                        val data= hashMapOf("uid" to firebaseAuth.getCurrentUser()?.getUid().toString(),
                        "roll" to email,
                            "name" to name,
                            "hostel" to hostel,
                            "phone" to phone,
                            "carts" to cart,
                            "URL" to "",
                            "fav" to fav,
                            "favs" to favi,
                            "cart" to carti,
                            "orders" to orders,
                            )
                        Toast.makeText(this,"Data Created",Toast.LENGTH_SHORT).show()
                        db.collection("Users").document(firebaseAuth.getCurrentUser()?.getUid().toString()).set(data).addOnCompleteListener {
                            if(it.isSuccessful){
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                            }
                            else{
                                Toast.makeText(this,"Registration Failed.",Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this,Sign_in::class.java))
                                finish()
                            }
                        }

                    }
                    else{
                        Toast.makeText(this, "Account already exists",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,Sign_in::class.java))
                        finish()
                    }
                }
            }
        }
    }
}
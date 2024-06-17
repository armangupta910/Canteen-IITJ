package com.example.canteeniitj

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView

class profile : AppCompatActivity() {
    var imagi:Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"
        val image: CircleImageView = findViewById(R.id.profile_image)

        val db111 = Firebase.firestore
        db111.collection("Users")
            .document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get()
            .addOnSuccessListener {
                if (it.data?.get("URL") == "") {
                    image.setBackgroundResource(R.drawable.baseline_person_24)
                } else {
                    Glide.with(this).load(it.data?.get("URL")).into(image)
                }
            }
        val button: Button = findViewById(R.id.updatebutton)
        button.visibility = View.GONE
        val name = findViewById<EditText>(R.id.input_name)
        name.visibility = View.GONE
        val phone: EditText = findViewById(R.id.input_phone)
        phone.visibility = View.GONE
        val email: EditText = findViewById(R.id.input_email)
        email.visibility = View.GONE
        val hostel: EditText = findViewById(R.id.input_hostel)
        hostel.visibility = View.GONE
        val text: TextView = findViewById(R.id.clicktoupdatetext)
        text.visibility = View.GONE
        findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
        val db = Firebase.firestore
        val y = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {
            imagi = it
            image.setImageURI(it)
        }
        image.setOnClickListener {
            y.launch("image/*")
        }
        db.collection("Users").get().addOnSuccessListener {
            for (i in it) {
                if (i.id == FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()) {
                    Glide.with(this).load(i.data.get("URL")).into(image)
                    name.setText(i.data.get("name").toString())
                    email.setText(i.data.get("roll").toString())
                    hostel.setText(i.data.get("hostel").toString())
                    findViewById<EditText>(R.id.input_phone).setText(i.data.get("phone").toString())
                    button.visibility = View.VISIBLE
                    name.visibility = View.VISIBLE
                    phone.visibility = View.VISIBLE
                    email.visibility = View.VISIBLE
                    hostel.visibility = View.VISIBLE
                    text.visibility = View.VISIBLE
                    findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
                }
            }
        }
        val firebaseAuth = FirebaseAuth.getInstance()
        image.setImageURI(imagi)
        button.setOnClickListener {
            val storageref = FirebaseStorage.getInstance()
            if (imagi == null) {
                Toast.makeText(this, "Please select a valid Image", Toast.LENGTH_SHORT).show()
            } else {
                storageref.getReference("Profile_Images")
                    .child(firebaseAuth.getCurrentUser()?.getUid().toString()).putFile(
                    imagi!!
                ).addOnSuccessListener {
                    Toast.makeText(this, "Image Uploaded", Toast.LENGTH_SHORT).show()
                    it.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                        val imageurl = it
                        db.collection("Users")
                            .document(firebaseAuth.getCurrentUser()?.getUid().toString())
                            .update("URL", imageurl).addOnSuccessListener {
                            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
//                        }
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, "Image Upload Failed", Toast.LENGTH_SHORT).show()
                    }
                }

                findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
                findViewById<Button>(R.id.updatebutton).visibility = View.GONE
                name.isEnabled = true
                name.setBackgroundResource(R.drawable.textboxbackbefore)
                phone.isEnabled = true
                phone.setBackgroundResource(R.drawable.textboxbackbefore)
                hostel.isEnabled = true
                hostel.setBackgroundResource(R.drawable.textboxbackbefore)
                text.visibility = View.VISIBLE
                button.text = "Update"
                findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
                findViewById<Button>(R.id.updatebutton).visibility = View.VISIBLE

                image.isClickable = true
                button.setOnClickListener {
                    button.visibility = View.GONE
                    findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
                    //Do Updation in Database
                    val data = hashMapOf(
                        "name" to name.text.toString(),
                        "hostel" to hostel.text.toString(),
                        "phone" to phone.text.toString()
                    )
                    db.collection("Users")
                        .document(firebaseAuth.getCurrentUser()?.getUid().toString()).update(
                        data as Map<String, Any>
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        } else {
                            Toast.makeText(this, "Updation Failed.", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this,MainActivity::class.java))
//                        finish()
                        }
                    }
                    //Finished Updation in Database

                }
            }


        }
    }
}
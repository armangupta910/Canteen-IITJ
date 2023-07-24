package com.example.canteeniitj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class images_upload_demo_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images_upload_demo)

        Toast.makeText(this,"Step 1",Toast.LENGTH_SHORT).show()
        val db=Firebase.firestore
        val data= hashMapOf(
            1 to hashMapOf(
                "Name" to "Tea",
                "Price" to "10",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            2 to hashMapOf(
                "Name" to "Coffee",
                "Price" to "16",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            3 to hashMapOf(
                "Name" to "Black Coffee",
                "Price" to "10",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            4 to hashMapOf(
                "Name" to "Black Tea",
                "Price" to "8",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            5 to hashMapOf(
                "Name" to "Haldi Milk",
                "Price" to "19",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            6 to hashMapOf(
                "Name" to "Samosa",
                "Price" to "20",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            7 to hashMapOf(
                "Name" to "Boiled Egg",
                "Price" to "10",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            8 to hashMapOf(
                "Name" to "Masala Boiled Egg",
                "Price" to "13",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            9 to hashMapOf(
                "Name" to "Omlette",
                "Price" to "26",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            10 to hashMapOf(
                "Name" to "Bread Omlette",
                "Price" to "32",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            11 to hashMapOf(
                "Name" to "Aloo Paratha",
                "Price" to "23",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            12 to hashMapOf(
                "Name" to "Paneer Paratha",
                "Price" to "34",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            13 to hashMapOf(
                "Name" to "Mix Paratha",
                "Price" to "26",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            14 to hashMapOf(
                "Name" to "Egg Paratha",
                "Price" to "37",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            15 to hashMapOf(
                "Name" to "Aloo Pyaz Paratha",
                "Price" to "26",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            16 to hashMapOf(
                "Name" to "Paneer Pyaz Paratha",
                "Price" to "37",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            17 to hashMapOf(
                "Name" to "Cold Coffe",
                "Price" to "26",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            18 to hashMapOf(
                "Name" to "French Fries",
                "Price" to "53",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            19 to hashMapOf(
                "Name" to "Honey Chilli Potato",
                "Price" to "63",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            ),
            20 to hashMapOf(
                "Name" to "Red Sauce Paasta",
                "Price" to "53",
                "Sum" to "0",
                "Total" to "0",
                "ImageURL" to ""
            )

        )
        Toast.makeText(this,"Step 2",Toast.LENGTH_SHORT).show()
        for (i in 1..20) {
            data[i]?.let { db.collection("Menu_Items").document(i.toString()).set(it) }?.addOnCompleteListener{
                if(it.isSuccessful){
                    findViewById<ProgressBar>(R.id.progressBar2).visibility= View.GONE
                    Toast.makeText(this,"Data Uploaded",Toast.LENGTH_SHORT).show()

                }
                else{
                    Toast.makeText(this,"Data Upload Failed",Toast.LENGTH_SHORT).show()
                }
            }
        }





    }
}
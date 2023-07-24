package com.example.canteeniitj

import android.animation.Animator
import android.app.Dialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class cart_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_page)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Cart"
        var data:HashMap<String,Map<String,String>> = hashMapOf()
        findViewById<Button>(R.id.checkout).setOnClickListener {
            var avail:MutableList<String> = mutableListOf()
            var sending:String = ""
            Firebase.firestore.collection("avail").document("avail").get().addOnSuccessListener {
                avail=it.data?.get("avail1") as MutableList<String>
                for(i in avail){
                    sending+=i
                }
                val intent=Intent(this,checkout::class.java)
                intent.putExtra("avail",sending)
                startActivity(intent)
            }
        }

        data= hashMapOf(
            "1" to mapOf<String,String>(
                "Name" to "Tea",
                "Price" to "10",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem1.jpg?alt=media&token=5e03cae7-3123-44e7-8499-6bb754a461fa"
            ),
            "2" to mapOf<String,String>(
                "Name" to "Coffee",
                "Price" to "16",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem2.jpg?alt=media&token=a304b5b6-1688-45aa-8db3-fda0eb756848"),
            "3" to mapOf<String,String>(
                "Name" to "Black Coffee",
                "Price" to "10",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem3.jpg?alt=media&token=f9430def-0531-495c-a213-60d23232f344"
            ),
            "4" to mapOf<String,String>(
                "Name" to "Black Tea",
                "Price" to "8",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "5" to mapOf<String,String>(
                "Name" to "Hot Milk",
                "Price" to "19",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem5.jpg?alt=media&token=067ac8f0-65a6-4849-9bdc-7d36c24e710b"
            ),
            "6" to mapOf<String,String>(
                "Name" to "Haldi Milk",
                "Price" to "19",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "7" to mapOf<String,String>(
                "Name" to "Samosa",
                "Price" to "20",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "8" to mapOf<String,String>(
                "Name" to "Boiled Egg",
                "Price" to "10",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "9" to mapOf<String,String>(
                "Name" to "Masala Boiled Egg",
                "Price" to "13",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "10" to mapOf<String,String>(
                "Name" to "Omlette",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "11" to mapOf<String,String>(
                "Name" to "Bread Omlette",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "12" to mapOf<String,String>(
                "Name" to "Aloo Paratha",
                "Price" to "23",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "13" to mapOf<String,String>(
                "Name" to "Paneer Paratha",
                "Price" to "34",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "14" to mapOf<String,String>(
                "Name" to "Mix Paratha",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "15" to mapOf<String,String>(
                "Name" to "Egg Paratha",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "16" to mapOf<String,String>(
                "Name" to "Aloo Pyaz Paratha",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "17" to mapOf<String,String>(
                "Name" to "Paneer Pyaz Paratha",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "18" to mapOf<String,String>(
                "Name" to "Cold Coffee",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "19" to mapOf<String,String>(
                "Name" to "French Fries",
                "Price" to "53",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "20" to mapOf<String,String>(
                "Name" to "Honey Chilli Potato",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "21" to mapOf<String,String>(
                "Name" to "Red Sauce Paasta",
                "Price" to "53",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "22" to mapOf<String,String>(
                "Name" to "White Sauce Paasta",
                "Price" to "68",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "23" to mapOf<String,String>(
                "Name" to "Veg Fried Rice",
                "Price" to "47",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "24" to mapOf<String,String>(
                "Name" to "Egg Fried Rice",
                "Price" to "58",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "25" to mapOf<String,String>(
                "Name" to "Chicken Fried Rice",
                "Price" to "84",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "26" to mapOf<String,String>(
                "Name" to "Veg Noodles",
                "Price" to "47",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "27" to mapOf<String,String>(
                "Name" to "Hakka Noodles",
                "Price" to "58",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "28" to mapOf<String,String>(
                "Name" to "Chicken Noodles",
                "Price" to "95",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "29" to mapOf<String,String>(
                "Name" to "Chicken 65",
                "Price" to "158",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "30" to mapOf<String,String>(
                "Name" to "Chilly Chicken",
                "Price" to "79",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "31" to mapOf<String,String>(
                "Name" to "Chicken Chilly",
                "Price" to "158",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "32" to mapOf<String,String>(
                "Name" to "Crispy Chicken",
                "Price" to "158",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "33" to mapOf<String,String>(
                "Name" to "Plain Maggi",
                "Price" to "21",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "34" to mapOf<String,String>(
                "Name" to "Veg Maggi",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "35" to mapOf<String,String>(
                "Name" to "Schezwan Maggi",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "36" to mapOf<String,String>(
                "Name" to "Paneer Cheese Sandwich",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "37" to mapOf<String,String>(
                "Name" to "Veg Sandwich",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "38" to mapOf<String,String>(
                "Name" to "Veg Cheese Sandwich",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "39" to mapOf<String,String>(
                "Name" to "Chicken Sandwich",
                "Price" to "60",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "40" to mapOf<String,String>(
                "Name" to "Chicken Burger",
                "Price" to "60",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "41" to mapOf<String,String>(
                "Name" to "Cheese Garlic Bread",
                "Price" to "99",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "42" to mapOf<String,String>(
                "Name" to "Chicken Garlic Bread",
                "Price" to "139",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "43" to mapOf<String,String>(
                "Name" to "Onion Pizza",
                "Price" to "68",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "44" to mapOf<String,String>(
                "Name" to "Plain Cheese Pizza",
                "Price" to "95",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "45" to mapOf<String,String>(
                "Name" to "Onion Capsicum Pizza",
                "Price" to "116",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "46" to mapOf<String,String>(
                "Name" to "Corn Pizza",
                "Price" to "99",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "47" to mapOf<String,String>(
                "Name" to "Chicken Pizza",
                "Price" to "130",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "48" to mapOf<String,String>(
                "Name" to "Veg Cheese Pizza",
                "Price" to "120",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "49" to mapOf<String,String>(
                "Name" to "Paneer Pizza",
                "Price" to "120",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "50" to mapOf<String,String>(
                "Name" to "Aloo Tikki Burger",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "51" to mapOf<String,String>(
                "Name" to "Veg Burger",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "52" to mapOf<String,String>(
                "Name" to "Veg Cheese Burger",
                "Price" to "53",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "53" to mapOf<String,String>(
                "Name" to "Veg Biryani",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "54" to mapOf<String,String>(
                "Name" to "Egg Biryani",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "55" to mapOf<String,String>(
                "Name" to "Chicken Biryani 2 Pieces",
                "Price" to "74",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "56" to mapOf<String,String>(
                "Name" to "French Fries 3 Pieces",
                "Price" to "105",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "57" to mapOf<String,String>(
                "Name" to "Egg Bhurji",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "58" to mapOf<String,String>(
                "Name" to "Paneer Chilli 6 Piece",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "59" to mapOf<String,String>(
                "Name" to "French Fries 10 Piece",
                "Price" to "116",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "60" to mapOf<String,String>(
                "Name" to "Veg Manchurian",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "61" to mapOf<String,String>(
                "Name" to "Dry Manchurian",
                "Price" to "79",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "62" to mapOf<String,String>(
                "Name" to "Milk Packet",
                "Price" to "25",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "63" to mapOf<String,String>(
                "Name" to "Curd Packet",
                "Price" to "21",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "64" to mapOf<String,String>(
                "Name" to "Monchow Soup",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "65" to mapOf<String,String>(
                "Name" to "Plain Naan",
                "Price" to "23",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "66" to mapOf<String,String>(
                "Name" to "Butter Naan",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "67" to mapOf<String,String>(
                "Name" to "Garlic Naan",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "68" to mapOf<String,String>(
                "Name" to "Stuffed Naan",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "69" to mapOf<String,String>(
                "Name" to "Tandoori Butter Roti",
                "Price" to "21",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "70" to mapOf<String,String>(
                "Name" to "Tandoori Plain Roti",
                "Price" to "16",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "71" to mapOf<String,String>(
                "Name" to "Laccha Paratha",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "72" to mapOf<String,String>(
                "Name" to "Kadai Paneer",
                "Price" to "147",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "73" to mapOf<String,String>(
                "Name" to "Handi Paneer",
                "Price" to "147",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "74" to mapOf<String,String>(
                "Name" to "Shahi Paneer",
                "Price" to "137",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "75" to mapOf<String,String>(
                "Name" to "Butter Paneer Masala",
                "Price" to "147",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "76" to mapOf<String,String>(
                "Name" to "Paneer Lababdar",
                "Price" to "179",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "77" to mapOf<String,String>(
                "Name" to "Paneer Kolhapuri",
                "Price" to "149",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "78" to mapOf<String,String>(
                "Name" to "Paneer Do Pyaza",
                "Price" to "139",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "79" to mapOf<String,String>(
                "Name" to "Dal Fry",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "80" to mapOf<String,String>(
                "Name" to "Dal Tadka",
                "Price" to "95",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "81" to mapOf<String,String>(
                "Name" to "Mix Veg",
                "Price" to "74",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "82" to mapOf<String,String>(
                "Name" to "Aloo Tamatar",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "83" to mapOf<String,String>(
                "Name" to "Sev Tomato",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "84" to mapOf<String,String>(
                "Name" to "Egg Curry",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "85" to mapOf<String,String>(
                "Name" to "Chicken Curry",
                "Price" to "95",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "86" to mapOf<String,String>(
                "Name" to "Kadai Chicken",
                "Price" to "168",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "87" to mapOf<String,String>(
                "Name" to "Butter Chicken Masala",
                "Price" to "179",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "88" to mapOf<String,String>(
                "Name" to "Tawa Chicken",
                "Price" to "168",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "89" to mapOf<String,String>(
                "Name" to "Punjabi Chicken",
                "Price" to "168",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "90" to mapOf<String,String>(
                "Name" to "Chicken Masala",
                "Price" to "149",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "91" to mapOf<String,String>(
                "Name" to "Chicken Kolhapuri",
                "Price" to "149",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "92" to mapOf<String,String>(
                "Name" to "Chicken Do Pyaza",
                "Price" to "158",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "93" to mapOf<String,String>(
                "Name" to "Marwari Chicken",
                "Price" to "159",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "94" to mapOf<String,String>(
                "Name" to "Plain Rice",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "95" to mapOf<String,String>(
                "Name" to "Idli",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "96" to mapOf<String,String>(
                "Name" to "Plain Dosa",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "97" to mapOf<String,String>(
                "Name" to "Masala Dosa",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "98" to mapOf<String,String>(
                "Name" to "Paneer Dosa",
                "Price" to "74",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "99" to mapOf<String,String>(
                "Name" to "Carrot Juice",
                "Price" to "30",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "100" to mapOf<String,String>(
                "Name" to "Mix Veg Juice",
                "Price" to "40",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "101" to mapOf<String,String>(
                "Name" to "Pineapple Shake",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "102" to mapOf<String,String>(
                "Name" to "Mango Shake",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "103" to mapOf<String,String>(
                "Name" to "Banana Shake",
                "Price" to "40",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "104" to mapOf<String,String>(
                "Name" to "Strawberry Shake",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "105" to mapOf<String,String>(
                "Name" to "Chocolate Shake",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            ),
            "106" to mapOf<String,String>(
                "Name" to "Ice Cream",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Fitem4.jpg?alt=media&token=ce7ba5bb-2019-4866-803b-26dc1da45e4f"
            )
        )

        //

        val db=Firebase.firestore
        db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
            var hehe:MutableList<Int> = mutableListOf()
            val haha=it.data?.get("cart") as MutableList<Int>
            for(i in haha){
                db.collection("Menu_Items").document(i.toString()).get().addOnSuccessListener {
                    if(it.data?.get("Availabilty")=="1"){
                        hehe.add(i)
                    }
                }
            }
            Log.d(TAG,"copied => ${hehe}")
            Log.d(TAG,"database => ${haha}")
            val x=findViewById<RecyclerView>(R.id.recycler_for_menu_items1)
            val y=adaptoforcart(this,data,haha)
            x.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
            x.adapter=y

        }

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.findViewById<TextView>(R.id.message).text="Preparing Cart..."
        dialog.findViewById<ProgressBar>(R.id.progiii).visibility= View.GONE
        val anim=dialog.findViewById<LottieAnimationView>(R.id.tick)
        anim.visibility= View.VISIBLE
        anim.playAnimation()
        dialog.show()

        anim.addAnimatorListener(object : Animator.AnimatorListener{

            override fun onAnimationStart(p0: Animator) {
                Log.d(ContentValues.TAG,"Animation Started")
            }

            override fun onAnimationEnd(p0: Animator) {
                dialog.hide()
            }

            override fun onAnimationCancel(p0: Animator) {
                TODO("Not yet implemented")
            }

            override fun onAnimationRepeat(p0: Animator) {
                TODO("Not yet implemented")
            }


        })
        Log.d(TAG,"copied => ")
        //

//        val x=findViewById<RecyclerView>(R.id.recycler_for_menu_items1)
//        val y=adaptoforcart(this,data)
//        x.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        x.adapter=y


    }
}
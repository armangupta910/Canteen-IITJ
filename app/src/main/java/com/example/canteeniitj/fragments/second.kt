package com.example.canteeniitj.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.canteeniitj.R
import com.example.canteeniitj.fragment_adaptor
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class second : Fragment() {
    var list:MutableList<String> = mutableListOf()
    var search10: SearchView? = activity?.let { SearchView(it) }

//    fun onBackPressed() {
//        if(search10?.isIconified==false){
//            list.clear()
//            for(i in 1..106){
//                temparray.add(i.toString())
//            }
////            findViewById<RecyclerView>(R.id.recycler_for_menu_items).adapter!!.notifyDataSetChanged()
//            search10.isIconified=true
//        }
//        else{
//            super.onBackPressed()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val demo = inflater.inflate(R.layout.fragment_second, container, false)
        lateinit var y:fragment_adaptor
        val db = Firebase.firestore
        var data: HashMap<String, Map<String, String>> = hashMapOf()




        data= hashMapOf(
            "1" to mapOf<String,String>(
                "Name" to "Tea",
                "Price" to "10",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "2" to mapOf<String,String>(
                "Name" to "Coffee",
                "Price" to "16",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"),
            "3" to mapOf<String,String>(
                "Name" to "Black Coffee",
                "Price" to "10",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "4" to mapOf<String,String>(
                "Name" to "Black Tea",
                "Price" to "8",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "5" to mapOf<String,String>(
                "Name" to "Hot Milk",
                "Price" to "19",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "6" to mapOf<String,String>(
                "Name" to "Haldi Milk",
                "Price" to "19",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "7" to mapOf<String,String>(
                "Name" to "Samosa",
                "Price" to "20",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "8" to mapOf<String,String>(
                "Name" to "Boiled Egg",
                "Price" to "10",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "9" to mapOf<String,String>(
                "Name" to "Masala Boiled Egg",
                "Price" to "13",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "10" to mapOf<String,String>(
                "Name" to "Omlette",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "11" to mapOf<String,String>(
                "Name" to "Bread Omlette",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "12" to mapOf<String,String>(
                "Name" to "Aloo Paratha",
                "Price" to "23",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "13" to mapOf<String,String>(
                "Name" to "Paneer Paratha",
                "Price" to "34",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "14" to mapOf<String,String>(
                "Name" to "Mix Paratha",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "15" to mapOf<String,String>(
                "Name" to "Egg Paratha",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "16" to mapOf<String,String>(
                "Name" to "Aloo Pyaz Paratha",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "17" to mapOf<String,String>(
                "Name" to "Paneer Pyaz Paratha",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "18" to mapOf<String,String>(
                "Name" to "Cold Coffee",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "19" to mapOf<String,String>(
                "Name" to "French Fries",
                "Price" to "53",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "20" to mapOf<String,String>(
                "Name" to "Honey Chilli Potato",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "21" to mapOf<String,String>(
                "Name" to "Red Sauce Paasta",
                "Price" to "53",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "22" to mapOf<String,String>(
                "Name" to "White Sauce Paasta",
                "Price" to "68",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "23" to mapOf<String,String>(
                "Name" to "Veg Fried Rice",
                "Price" to "47",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "24" to mapOf<String,String>(
                "Name" to "Egg Fried Rice",
                "Price" to "58",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "25" to mapOf<String,String>(
                "Name" to "Chicken Fried Rice",
                "Price" to "84",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "26" to mapOf<String,String>(
                "Name" to "Veg Noodles",
                "Price" to "47",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "27" to mapOf<String,String>(
                "Name" to "Hakka Noodles",
                "Price" to "58",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "28" to mapOf<String,String>(
                "Name" to "Chicken Noodles",
                "Price" to "95",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "29" to mapOf<String,String>(
                "Name" to "Chicken 65",
                "Price" to "158",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "30" to mapOf<String,String>(
                "Name" to "Chilly Chicken",
                "Price" to "79",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "31" to mapOf<String,String>(
                "Name" to "Chicken Chilly",
                "Price" to "158",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "32" to mapOf<String,String>(
                "Name" to "Crispy Chicken",
                "Price" to "158",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "33" to mapOf<String,String>(
                "Name" to "Plain Maggi",
                "Price" to "21",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "34" to mapOf<String,String>(
                "Name" to "Veg Maggi",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "35" to mapOf<String,String>(
                "Name" to "Schezwan Maggi",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "36" to mapOf<String,String>(
                "Name" to "Paneer Cheese Sandwich",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "37" to mapOf<String,String>(
                "Name" to "Veg Sandwich",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "38" to mapOf<String,String>(
                "Name" to "Veg Cheese Sandwich",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "39" to mapOf<String,String>(
                "Name" to "Chicken Sandwich",
                "Price" to "60",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "40" to mapOf<String,String>(
                "Name" to "Chicken Burger",
                "Price" to "60",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "41" to mapOf<String,String>(
                "Name" to "Cheese Garlic Bread",
                "Price" to "99",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "42" to mapOf<String,String>(
                "Name" to "Chicken Garlic Bread",
                "Price" to "139",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "43" to mapOf<String,String>(
                "Name" to "Onion Pizza",
                "Price" to "68",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "44" to mapOf<String,String>(
                "Name" to "Plain Cheese Pizza",
                "Price" to "95",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "45" to mapOf<String,String>(
                "Name" to "Onion Capsicum Pizza",
                "Price" to "116",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "46" to mapOf<String,String>(
                "Name" to "Corn Pizza",
                "Price" to "99",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "47" to mapOf<String,String>(
                "Name" to "Chicken Pizza",
                "Price" to "130",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "48" to mapOf<String,String>(
                "Name" to "Veg Cheese Pizza",
                "Price" to "120",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "49" to mapOf<String,String>(
                "Name" to "Paneer Pizza",
                "Price" to "120",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "50" to mapOf<String,String>(
                "Name" to "Aloo Tikki Burger",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "51" to mapOf<String,String>(
                "Name" to "Veg Burger",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "52" to mapOf<String,String>(
                "Name" to "Veg Cheese Burger",
                "Price" to "53",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "53" to mapOf<String,String>(
                "Name" to "Veg Biryani",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "54" to mapOf<String,String>(
                "Name" to "Egg Biryani",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "55" to mapOf<String,String>(
                "Name" to "Chicken Biryani 2 Pieces",
                "Price" to "74",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "56" to mapOf<String,String>(
                "Name" to "French Fries 3 Pieces",
                "Price" to "105",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "57" to mapOf<String,String>(
                "Name" to "Egg Bhurji",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "58" to mapOf<String,String>(
                "Name" to "Paneer Chilli 6 Piece",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "59" to mapOf<String,String>(
                "Name" to "French Fries 10 Piece",
                "Price" to "116",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "60" to mapOf<String,String>(
                "Name" to "Veg Manchurian",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "61" to mapOf<String,String>(
                "Name" to "Dry Manchurian",
                "Price" to "79",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "62" to mapOf<String,String>(
                "Name" to "Milk Packet",
                "Price" to "25",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "63" to mapOf<String,String>(
                "Name" to "Curd Packet",
                "Price" to "21",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "64" to mapOf<String,String>(
                "Name" to "Monchow Soup",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "65" to mapOf<String,String>(
                "Name" to "Plain Naan",
                "Price" to "23",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "66" to mapOf<String,String>(
                "Name" to "Butter Naan",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "67" to mapOf<String,String>(
                "Name" to "Garlic Naan",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "68" to mapOf<String,String>(
                "Name" to "Stuffed Naan",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "69" to mapOf<String,String>(
                "Name" to "Tandoori Butter Roti",
                "Price" to "21",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "70" to mapOf<String,String>(
                "Name" to "Tandoori Plain Roti",
                "Price" to "16",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "71" to mapOf<String,String>(
                "Name" to "Laccha Paratha",
                "Price" to "26",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "72" to mapOf<String,String>(
                "Name" to "Kadai Paneer",
                "Price" to "147",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "73" to mapOf<String,String>(
                "Name" to "Handi Paneer",
                "Price" to "147",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "74" to mapOf<String,String>(
                "Name" to "Shahi Paneer",
                "Price" to "137",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "75" to mapOf<String,String>(
                "Name" to "Butter Paneer Masala",
                "Price" to "147",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "76" to mapOf<String,String>(
                "Name" to "Paneer Lababdar",
                "Price" to "179",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "77" to mapOf<String,String>(
                "Name" to "Paneer Kolhapuri",
                "Price" to "149",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "78" to mapOf<String,String>(
                "Name" to "Paneer Do Pyaza",
                "Price" to "139",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "79" to mapOf<String,String>(
                "Name" to "Dal Fry",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "80" to mapOf<String,String>(
                "Name" to "Dal Tadka",
                "Price" to "95",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "81" to mapOf<String,String>(
                "Name" to "Mix Veg",
                "Price" to "74",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "82" to mapOf<String,String>(
                "Name" to "Aloo Tamatar",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "83" to mapOf<String,String>(
                "Name" to "Sev Tomato",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "84" to mapOf<String,String>(
                "Name" to "Egg Curry",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "85" to mapOf<String,String>(
                "Name" to "Chicken Curry",
                "Price" to "95",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "86" to mapOf<String,String>(
                "Name" to "Kadai Chicken",
                "Price" to "168",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "87" to mapOf<String,String>(
                "Name" to "Butter Chicken Masala",
                "Price" to "179",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "88" to mapOf<String,String>(
                "Name" to "Tawa Chicken",
                "Price" to "168",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "89" to mapOf<String,String>(
                "Name" to "Punjabi Chicken",
                "Price" to "168",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "90" to mapOf<String,String>(
                "Name" to "Chicken Masala",
                "Price" to "149",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "91" to mapOf<String,String>(
                "Name" to "Chicken Kolhapuri",
                "Price" to "149",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "92" to mapOf<String,String>(
                "Name" to "Chicken Do Pyaza",
                "Price" to "158",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "93" to mapOf<String,String>(
                "Name" to "Marwari Chicken",
                "Price" to "159",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "94" to mapOf<String,String>(
                "Name" to "Plain Rice",
                "Price" to "32",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "95" to mapOf<String,String>(
                "Name" to "Idli",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "96" to mapOf<String,String>(
                "Name" to "Plain Dosa",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "97" to mapOf<String,String>(
                "Name" to "Masala Dosa",
                "Price" to "63",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "98" to mapOf<String,String>(
                "Name" to "Paneer Dosa",
                "Price" to "74",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "99" to mapOf<String,String>(
                "Name" to "Carrot Juice",
                "Price" to "30",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "100" to mapOf<String,String>(
                "Name" to "Mix Veg Juice",
                "Price" to "40",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "101" to mapOf<String,String>(
                "Name" to "Pineapple Shake",
                "Price" to "37",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "102" to mapOf<String,String>(
                "Name" to "Mango Shake",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "103" to mapOf<String,String>(
                "Name" to "Banana Shake",
                "Price" to "40",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "104" to mapOf<String,String>(
                "Name" to "Strawberry Shake",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "105" to mapOf<String,String>(
                "Name" to "Chocolate Shake",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            ),
            "106" to mapOf<String,String>(
                "Name" to "Ice Cream",
                "Price" to "42",
                "Ratings" to "5",
                "URL" to "https://firebasestorage.googleapis.com/v0/b/canteen-iitj.appspot.com/o/menu_images%2Ficon.jpg?alt=media&token=22c45e52-5b79-43a8-bd79-2dab1d4a7b56"
            )
        )

        db.collection("Menu_Items").get().addOnSuccessListener {
            for (i in it) {
                if (i.data.get("Category") == "2") {
                    list.add(i.id)
                }
            }
            val x = demo.findViewById<RecyclerView>(R.id.recycler_fragment2)
            y = activity?.let { it1 -> fragment_adaptor(it1, list, data) }!!
            demo.findViewById<ProgressBar>(R.id.progress_2).visibility=View.GONE
            x.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            x.adapter = y

            demo.findViewById<SwipeRefreshLayout>(R.id.swipe2).setOnRefreshListener {
                list.clear()
                db.collection("Menu_Items").get().addOnSuccessListener {
                    for (i in it) {
                        if (i.data.get("Category") == "2") {
                            list.add(i.id)
                        }
                    }
                    y?.notifyDataSetChanged()
                    Handler().postDelayed({
                        demo.findViewById<SwipeRefreshLayout>(R.id.swipe2).isRefreshing = false
                    }, 1200)
                }
            }
        }

        search10 = demo.findViewById<SearchView>(R.id.search_view) as SearchView
        search10!!.setOnCloseListener {
            list.clear()
            db.collection("Menu_Items").get().addOnSuccessListener {
                for (i in it) {
                    if (i.data.get("Category") == "2") {
                        list.add(i.id)
                    }
                }
                y!!.notifyDataSetChanged()
            }
            return@setOnCloseListener false
        }

        search10!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                list.clear()
                var haha: MutableList<String> = mutableListOf()
                if (newText != "") {
                    var hihu = newText!!.lowercase()
                    db.collection("Menu_Items").get().addOnSuccessListener {
                        for (i in it) {
                            if (i.data.get("Category") == "2") {
                                haha.add(i.id)
                            }
                        }
                        val seti:MutableList<String> = mutableListOf()
                        Log.d(TAG,"haha => ${haha}")
                        for (i in haha) {
                            if (data.get(i)!!.get("Name")!!.lowercase().contains(hihu) == true) {
                                seti.add(i)
                            }
                        }
                        for(i in seti){
                            if(list.contains(i)==false){
                                list.add(i)
                            }
                        }
                        Log.d(TAG,"list => ${list}")
                        y.notifyDataSetChanged()
                    }

                } else {
                    list.clear()
                    db.collection("Menu_Items").get().addOnSuccessListener {
                        for (i in it) {
                            if (i.data.get("Category") == "2") {
                                list.add(i.id)
                            }
                        }
                        y.notifyDataSetChanged()
                    }

//                    findViewById<RecyclerView>(R.id.recycler_for_menu_items).adapter!!.notifyDataSetChanged()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }


        })
        return demo

    }
}
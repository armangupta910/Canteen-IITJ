package com.example.canteeniitj

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class view_bill : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_bill)
        var order_id=intent.getStringExtra("orderid")
        Log.d(TAG,"order_id => ${order_id}")

        var data:HashMap<String,Map<String,String>> = hashMapOf()
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
            )
        )


        var a:MutableList<String> = mutableListOf()
        var b:Map<String,Int>
        var c:HashMap<String,Map<String,Int>>

        Firebase.firestore.collection("orders").document("orders").get().addOnSuccessListener {
            c=it.data?.get("orders") as HashMap<String, Map<String, Int>>
            b=c.get(order_id) as Map<String, Int>
            for(i in b){
                a.add(i.key)
            }
            Log.d(TAG,"a => ${a}")
            Log.d(TAG,"b => ${b}")
            val z=findViewById<RecyclerView>(R.id.recycler_for_view_bill)
            val y=adaptor_for_current_order(this,a,b,data)
            z.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
            z.adapter=y
        }


    }
}
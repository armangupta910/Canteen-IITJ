package com.example.canteeniitj

import  android.annotation.SuppressLint
import android.app.ApplicationErrorReport.CrashInfo
import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.Profile
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.canteeniitj.fragments.viewpager_adaptor
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.w3c.dom.Text
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var data:HashMap<String,Map<String,String>>
    var tempdata:HashMap<String,Map<String,String>> = hashMapOf()
    var temparray:MutableList<String> = mutableListOf()
    private lateinit var search10:SearchView
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(search10.isIconified==false){
            temparray.clear()
            for(i in 1..106){
                temparray.add(i.toString())
            }
//            findViewById<RecyclerView>(R.id.recycler_for_menu_items).adapter!!.notifyDataSetChanged()
            search10.isIconified=true
        }
        else{
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.search_bar,menu)
//        val item=menu?.findItem(R.id.search_bar1)
        supportActionBar?.subtitle="IIT Jodhpur"

//        search10 = item!!.actionView as SearchView
//        search10.setOnCloseListener {
//            temparray.clear()
//            tempdata.clear()
//            tempdata.putAll(data)
//            for(i in 1..106){
//                temparray.add(i.toString())
//            }
////            findViewById<RecyclerView>(R.id.recycler_for_menu_items).adapter!!.notifyDataSetChanged()
////            search10.isIconified=true
//            return@setOnCloseListener false
//        }
//        search10.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(newText: String?): Boolean {
//                tempdata.clear()
//                temparray.clear()
//                if(newText!=""){
//                    var hihu=newText!!.lowercase()
//                    for(i in data){
//                        if(i.value.get("Name")?.lowercase()?.contains(hihu) == true){
//                            tempdata.put(i.key,i.value)
//                            temparray.add(i.key)
//                        }
//                    }
////                    findViewById<RecyclerView>(R.id.recycler_for_menu_items).adapter!!.notifyDataSetChanged()
//                    Log.d(TAG,"tempdata => ${tempdata}")
//                    Log.d(TAG,"temparray => ${temparray}")
//                }
//                else{
//                    temparray.clear()
//                    tempdata.clear()
//                    tempdata.putAll(data)
//                    for(i in 1..106){
//                        temparray.add(i.toString())
//                    }
////                    findViewById<RecyclerView>(R.id.recycler_for_menu_items).adapter!!.notifyDataSetChanged()
//                }
//                return true
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onQueryTextChange(newText: String?): Boolean {
////                tempdata.clear()
////                temparray.clear()
////
////                if(newText!=""){
////                    var hihu=newText!!.lowercase()
////                    for(i in data){
////                        if(i.value.get("Name")?.lowercase()?.contains(hihu) == true){
////                            tempdata.put(i.key,i.value)
////                            temparray.add(i.key)
////                        }
////                    }
////                    findViewById<RecyclerView>(R.id.recycler_for_menu_items).adapter!!.notifyDataSetChanged()
////                    Log.d(TAG,"tempdata => ${tempdata}")
////                    Log.d(TAG,"temparray => ${temparray}")
////                }
////                else{
////                    temparray.clear()
////                    tempdata.clear()
////                    tempdata.putAll(data)
////                    for(i in 1..106){
////                        temparray.add(i.toString())
////                    }
////                    findViewById<RecyclerView>(R.id.recycler_for_menu_items).adapter!!.notifyDataSetChanged()
////
////                }
//                return true
//
//            }
//
//        })
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Drawer Layout

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.findViewById<TextView>(R.id.message).text="Setting Up Things..."
        val anim=dialog.findViewById<LottieAnimationView>(R.id.bowl)
        anim.playAnimation()
        anim.visibility=View.VISIBLE
        dialog.findViewById<ProgressBar>(R.id.progiii).visibility=View.GONE
        dialog.show()
        Handler().postDelayed({dialog.hide()}
//            findViewById<RecyclerView>(R.id.recycler_for_menu_items).alpha= 1F}
            ,2500)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navview : NavigationView = findViewById(R.id.nav_view)
        val headerVire=navview.getHeaderView(0)
        headerVire.findViewById<ImageView>(R.id.prof_image).setOnClickListener {
            startActivity(Intent(this,profile::class.java))
        }
        val db=Firebase.firestore
        db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
            headerVire.findViewById<TextView>(R.id.name).text=it.data?.get("name") as String
            headerVire.findViewById<TextView>(R.id.rollnumber).setText(it.data!!.get("roll") as String)
            if(it.data?.get("URL")=="" || it.data?.get("URL")==null){
                findViewById<ImageView>(R.id.prof_image).setImageResource(R.drawable.baseline_person_24)
            }
            else{
                Glide.with(this).load(it.data!!.get("URL")).into(findViewById(R.id.prof_image))
            }
        }
        toggle=ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navview.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.Profile -> startActivity(Intent(this, profile::class.java))
                R.id.Cart -> {
                    val db = Firebase.firestore
                    var count = 0
                    db.collection("Users")
                        .document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString())
                        .get().addOnSuccessListener {
                        val demo: MutableList<Int> = it.data?.get("cart") as MutableList<Int>
                        for (i in demo) {
                            db.collection("Menu_Items").document(i.toString()).get()
                                .addOnSuccessListener {
                                    if (it.data?.get("Availabilty") == "1") {
                                        count++
                                    }
                                }
                        }

                            if (demo.size == 0) {
                                val dialog = Dialog(this)
                                dialog.setContentView(R.layout.dialog)
                                dialog.findViewById<TextView>(R.id.message).text =
                                    "The Cart seems to be Empty. Please add few items to view Cart"
                                dialog.findViewById<ProgressBar>(R.id.progiii).visibility = View.GONE
                                dialog.findViewById<Button>(R.id.gobackbutton).visibility = View.VISIBLE
                                dialog.findViewById<LottieAnimationView>(R.id.cati).visibility=View.VISIBLE
                                dialog.show()

                                dialog.findViewById<Button>(R.id.gobackbutton).setOnClickListener {
                                    dialog.hide()
                                }
                            } else {
                                startActivity(Intent(this, cart_page::class.java))
                            }

                    }

                }


                R.id.log_out->{
                    var token=getSharedPreferences("demo", Context.MODE_PRIVATE)
                    var editor=token.edit()
                    editor.putString("user","0")
                    editor.commit()
                    val dialog = Dialog(this)
                    dialog.setContentView(R.layout.dialog)
                    dialog.findViewById<TextView>(R.id.message).text="Logging you Out..."
                    dialog.show()
                    startActivity(Intent(this,Splash_Screen::class.java))
                    finish()
                    dialog.hide()
                }
                R.id.Favourites->{
                    val db=Firebase.firestore
                    db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
                        val demo:MutableList<Int> = it.data?.get("favs") as MutableList<Int>
                        if(demo.size==0){
                            val dialog=Dialog(this)
                            dialog.setContentView(R.layout.dialog)
                            dialog.findViewById<TextView>(R.id.message).text="The Favourites Tab seems to be Empty. Please add few items to view Favourites"
                            dialog.findViewById<ProgressBar>(R.id.progiii).visibility= View.GONE
                            dialog.findViewById<LottieAnimationView>(R.id.cati).visibility=View.VISIBLE
                            dialog.findViewById<Button>(R.id.gobackbutton).visibility=View.VISIBLE
                            val anim=dialog.findViewById<LottieAnimationView>(R.id.cati)
                            anim.playAnimation()
                            dialog.show()
                            dialog.findViewById<Button>(R.id.gobackbutton).setOnClickListener {
                                dialog.hide()
                            }
                        }
                        else{
                            startActivity(Intent(this,favourites::class.java))}
                    }
                }
                R.id.hostory->{
                    var demo:HashMap<String,HashMap<String,Int>> = hashMapOf()
                    db.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
                        demo=it.data?.get("orders") as HashMap<String, HashMap<String, Int>>
                        if(demo.size==0){
                            val dialog = Dialog(this)
                            dialog.setContentView(R.layout.dialog)
                            dialog.findViewById<TextView>(R.id.message).text="You haven't made any Orders yet"
                            dialog.findViewById<ProgressBar>(R.id.progiii).visibility= View.GONE
                            val anim=dialog.findViewById<LottieAnimationView>(R.id.cati)
                            anim.visibility=View.VISIBLE
                            anim.playAnimation()
                            dialog.show()
                            dialog.findViewById<Button>(R.id.gobackbutton).visibility=View.VISIBLE
                            dialog.findViewById<Button>(R.id.gobackbutton).setOnClickListener {
                                dialog.hide()
                            }
                        }
                        else{
                            startActivity(Intent(this,history::class.java))
                        }
                    }


                }
                else -> {
//                    Toast.makeText(this,"Profile Clicked",Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        //Drawer Layout

        //Getting Menu Data
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

        for(i in 1..106){
            temparray.add(i.toString())
        }
        tempdata.putAll(data)
//        val x=findViewById<RecyclerView>(R.id.recycler_for_menu_items)
//        val y=adaptor(this,temparray,data)
//        x.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        x.adapter=y
//
//        val swipe=findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
//        swipe.setOnRefreshListener{
//            y.notifyDataSetChanged()
//            Handler().postDelayed({
//                swipe.isRefreshing=false
//            },
//                1000
//            )
//
//        }

        val x=findViewById<TabLayout>(R.id.tab_layout)
        val y=findViewById<ViewPager2>(R.id.viewpager)
        val adap=viewpager_adaptor(supportFragmentManager,lifecycle)

        y.adapter=adap
        TabLayoutMediator(x,y){x,position->
            when(position){
                0->{
                    x.text="Beverages"
                }
                1->x.text="Starters"
                2->x.text="Main Course"
            }
        }.attach()

    }





}
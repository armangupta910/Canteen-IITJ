package com.example.canteeniitj

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView
import io.grpc.okhttp.internal.framed.FrameReader
import java.util.logging.Handler

class adaptor(val conti:Context, var data:HashMap<String,Map<String,String>>):RecyclerView.Adapter<adaptor.view_holder>() {

    class view_holder(itemview:View):RecyclerView.ViewHolder(itemview){
        var x:TextView=itemview.findViewById(R.id.name_of_food_item)
        var y:TextView=itemview.findViewById(R.id.price_of_food_item)
        var z:TextView=itemview.findViewById(R.id.ratings_of_food_item)
        var image:CircleImageView=itemview.findViewById(R.id.food_item_image)
        var cart:Button=itemview.findViewById(R.id.add_to_cart_button)
        var blackstar:ImageView=itemview.findViewById(R.id.starblack)
        var redstar:ImageView=itemview.findViewById(R.id.starred)
        var cardi:CardView=itemview.findViewById(R.id.cardformenu)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemview:View=LayoutInflater.from(parent.context).inflate(R.layout.cardforitems,parent,false)
        return view_holder(itemview)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: view_holder, position: Int) {

        val db=Firebase.firestore
        db.collection("Menu_Items").document((position+1).toString()).get().addOnSuccessListener {
            Log.d(TAG,"${it.data}")
            if(it.data?.get("Availabilty")=="0"){
                holder.cardi.setBackgroundResource(R.drawable.backfornotavail)
                holder.cart.text="Not Available"
                holder.cart.isEnabled=false
                holder.cart.setTextColor(R.color.black)
                holder.cart.setBackgroundResource(R.drawable.backfrobuttonnotavail)
            }
            if(it.data?.get("Availabilty")=="1"){
                holder.cardi.setBackgroundResource(R.drawable.backforcardandimage)
                holder.cart.text="Add to Cart"
                holder.cart.isEnabled=true
                holder.cart.setTextColor(R.color.black)
                holder.cart.setBackgroundResource(R.drawable.backforbutton)
            }
        }
        holder.x.text=data[(position+1).toString()]?.get("Name")
        holder.y.text="Price - "+data[(position+1).toString()]?.get("Price")
        holder.z.text="Rating - "+data[(position+1).toString()]?.get("Ratings")
        holder.image.setImageResource(R.drawable.baseline_person_24)
//        image_url[position]?.let { holder.image.setImageResource(it) }
        Glide.with(conti).load(data[(position+1).toString()]?.get("URL")).into(holder.image)
        val db1=Firebase.firestore
        var x=0
        db1.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
            var favi:HashMap<String,Int> =  it.data?.get("fav") as HashMap<String, Int>
            Log.d(TAG,"${favi}")
            x= favi.get((position+1).toString())!!
            if(x==1){
                holder.blackstar.visibility=View.GONE
                holder.redstar.visibility=View.VISIBLE

            }
            else{
                holder.blackstar.visibility=View.VISIBLE
                holder.redstar.visibility=View.GONE
            }
        }
        holder.cart.setOnClickListener {
            val dialog = Dialog(conti)
            dialog.setContentView(R.layout.dialog)
            dialog.findViewById<TextView>(R.id.message).text="Adding to Cart..."
            val tick=dialog.findViewById<LottieAnimationView>(R.id.tick)
            dialog.findViewById<ProgressBar>(R.id.progiii).visibility=View.GONE
            tick.visibility=View.VISIBLE
            tick.playAnimation()
            dialog.show()
            holder.cardi.alpha=0.5F
            val db=Firebase.firestore
            db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
                var listi = it.data?.get("cart") as MutableList<Int>
                var k=0
                for(x in listi){
                    if(x==(position+1)){
                        k=1
                    }
                }
                if(k==0){
                    listi.add(position+1)
                }

                db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("cart",listi).addOnSuccessListener {
                    Toast.makeText(conti,"List Updated",Toast.LENGTH_SHORT).show()
                }
                var carti: HashMap<String,Int> = it.data?.get("carts") as HashMap<String, Int>
                if(carti[(position+1).toString()]!=null){
                    carti[(position+1).toString()]= carti[(position+1).toString()]!! + 1
                }

                db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("carts",carti).addOnSuccessListener {
                    Toast.makeText(conti,"Added to Cart",Toast.LENGTH_SHORT).show()
                }

            }
            tick.addAnimatorListener(object : Animator.AnimatorListener{

                override fun onAnimationStart(p0: Animator) {
                    Log.d(TAG,"Animation Started")
                }

                override fun onAnimationEnd(p0: Animator) {
                    holder.cardi.alpha=1F
                    dialog.hide()
                }

                override fun onAnimationCancel(p0: Animator) {
                    TODO("Not yet implemented")
                }

                override fun onAnimationRepeat(p0: Animator) {
                    TODO("Not yet implemented")
                }


            })


        }

        holder.redstar.setOnClickListener {
            val dialog = Dialog(conti)
            dialog.setContentView(R.layout.dialog)
            dialog.findViewById<TextView>(R.id.message).text="Removing from Favourites..."
            dialog.findViewById<ProgressBar>(R.id.progiii).visibility=View.GONE
            val star=dialog.findViewById<LottieAnimationView>(R.id.blackstar)
            star.visibility=View.VISIBLE
            star.playAnimation()
            dialog.show()
            holder.cardi.alpha=0.5F
            var demo1:MutableList<Int>
            var demo:HashMap<String,Int> = hashMapOf()
            var newdemo1:MutableList<Int> = mutableListOf()
            db1.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
                holder.redstar.visibility=View.GONE
                holder.blackstar.visibility=View.VISIBLE
                demo1=it.data?.get("favs") as MutableList<Int>
                for(i in demo1){
                    if(i!=position+1) {
                        newdemo1.add(i)
                    }
                }
                db1.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("favs",newdemo1)
                demo=it.data?.get("fav") as HashMap<String, Int>
                demo[(position+1).toString()] = demo[(position+1).toString()]!!-1
                db1.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("fav",demo).addOnSuccessListener {
                    holder.cardi.alpha=1F
                    dialog.hide()
                    Toast.makeText(conti,"Removed from Favourites",Toast.LENGTH_SHORT).show()

                }
            }
            star.addAnimatorListener(object : Animator.AnimatorListener{

                override fun onAnimationStart(p0: Animator) {
                    Log.d(TAG,"Animation Started")
                }

                override fun onAnimationEnd(p0: Animator) {
                    holder.cardi.alpha=1F
                    dialog.hide()
                }

                override fun onAnimationCancel(p0: Animator) {
                    TODO("Not yet implemented")
                }

                override fun onAnimationRepeat(p0: Animator) {
                    TODO("Not yet implemented")
                }


            })
        }

        holder.blackstar.setOnClickListener {

            val dialog = Dialog(conti)
            dialog.setContentView(R.layout.dialog)
            dialog.findViewById<TextView>(R.id.message).text="Adding to Favourites..."
            dialog.findViewById<ProgressBar>(R.id.progiii).visibility=View.GONE
            val star=dialog.findViewById<LottieAnimationView>(R.id.star)
            star.visibility=View.VISIBLE
            star.playAnimation()
            dialog.show()
            holder.cardi.alpha=0.5F
            var demo1:MutableList<Int>
            var demo:HashMap<String,Int> = hashMapOf()
            db1.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
                demo1=it.data?.get("favs") as MutableList<Int>
                holder.redstar.visibility=View.VISIBLE
                holder.blackstar.visibility=View.GONE
                var x=0
                for(i in demo1){
                    if(i==(position+1)){
                        x=1
                    }
                }
                if(x==0){
                    demo1.add(position+1)
                }

                db1.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("favs",demo1)

                demo=it.data?.get("fav") as HashMap<String, Int>
                demo[(position+1).toString()] = demo[(position+1).toString()]!!+1
                db1.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("fav",demo).addOnSuccessListener {
                    holder.cardi.alpha=1F
                    dialog.hide()

                    Toast.makeText(conti,"Added to Favourites",Toast.LENGTH_SHORT).show()

                }
            }
            star.addAnimatorListener(object : Animator.AnimatorListener{

                override fun onAnimationStart(p0: Animator) {
                    Log.d(TAG,"Animation Started")
                }

                override fun onAnimationEnd(p0: Animator) {
                    holder.cardi.alpha=1F
                    dialog.hide()
                }

                override fun onAnimationCancel(p0: Animator) {
                    TODO("Not yet implemented")
                }

                override fun onAnimationRepeat(p0: Animator) {
                    TODO("Not yet implemented")
                }


            })
        }
//        holder.y.text=data[(position+1).toString()]?.get("phone")

    }
}
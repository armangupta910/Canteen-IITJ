package com.example.canteeniitj

import android.animation.Animator
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.translation.ViewTranslationRequest
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
import org.w3c.dom.Text
import java.util.logging.Handler

class adaptor_for_available_and_not_available(val conti:Context,var list:MutableList<String>, var data:HashMap<String,Map<String,String>>,var ref:String):RecyclerView.Adapter<adaptor_for_available_and_not_available.view_holder>() {

    class view_holder(itemview:View):RecyclerView.ViewHolder(itemview){

        var name:TextView = itemview.findViewById(R.id.name_of_food_item)
        var price:TextView = itemview.findViewById(R.id.price_of_food_item)
        var image:CircleImageView = itemview.findViewById(R.id.food_item_image)
        var button:Button = itemview.findViewById(R.id.add_to_cart_button)
        var card:CardView = itemview.findViewById(R.id.cardformenu)
        var star:CircleImageView = itemview.findViewById(R.id.starblack)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemview:View=LayoutInflater.from(parent.context).inflate(R.layout.cardforitems,parent,false)
        return view_holder(itemview)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
        holder.name.text="Name - "+data.get((position+1).toString())?.get("Name")
        holder.price.text="Price - "+data.get((position+1).toString())?.get("Price")
        Glide.with(conti).load(data.get((position+1).toString())?.get("URL")).circleCrop().into(holder.image)
        holder.star.visibility=View.GONE
        if(ref=="0"){
            holder.button.setText("Make Unavailable")
        }
        else{
            holder.button.setText("Make Available")
        }
        if(list[(position)]==ref){
            holder.card.visibility=View.GONE
        }

            holder.button.setOnClickListener {
                if(ref=="0"){
                    list[position]="0"
                    val db=Firebase.firestore
                    db.collection("avail").document("avail").update("avail1",list)
                    db.collection("Menu_Items").document((position+1).toString()).update("Availabilty","0")
                    Toast.makeText(conti,"Removed from Available",Toast.LENGTH_SHORT).show()
                    holder.card.visibility=View.GONE
                }
                else{
                    list[position]="1"
                    val db=Firebase.firestore
                    db.collection("avail").document("avail").update("avail1",list)
                    db.collection("Menu_Items").document((position+1).toString()).update("Availabilty","1")
                    Toast.makeText(conti,"Added to Available",Toast.LENGTH_SHORT).show()
                    holder.card.visibility=View.GONE
                }

            }

    }
}
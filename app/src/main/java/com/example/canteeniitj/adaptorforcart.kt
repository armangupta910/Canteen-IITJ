package com.example.canteeniitj

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
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
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView

class adaptoforcart(val conti:Context, var data:HashMap<String,Map<String,String>>,var list:MutableList<Int>):RecyclerView.Adapter<adaptoforcart.view_holder>() {

    class view_holder(itemview:View):RecyclerView.ViewHolder(itemview){
        var x:TextView=itemview.findViewById(R.id.name_of_food_item1)
        var y:TextView=itemview.findViewById(R.id.price_of_food_item1)
        var z:TextView=itemview.findViewById(R.id.ratings_of_food_item1)
        var image:CircleImageView=itemview.findViewById(R.id.food_item_image1)
        var k:TextView=itemview.findViewById(R.id.qunatity)
        var cardo:CardView=itemview.findViewById(R.id.cardid)
        var plus:Button=itemview.findViewById(R.id.plus)
        var minus:Button=itemview.findViewById(R.id.minus)
        var progi:ProgressBar = itemview.findViewById(R.id.progo)
        var red:ImageView=itemview.findViewById(R.id.red)
        var blacki:ImageView=itemview.findViewById(R.id.black)
        var butt:Button=itemview.findViewById(R.id.notavbutt)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemview:View=LayoutInflater.from(parent.context).inflate(R.layout.cardforcart,parent,false)
        return view_holder(itemview)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
        holder.red.visibility=View.GONE
        holder.blacki.visibility=View.GONE
        var x="1"
        var y=0
        val db= Firebase.firestore
        var cartii: HashMap<String, Int> = hashMapOf()

        //
        db.collection("Menu_Items").document(list[position].toString()).get().addOnSuccessListener {
            if(it.data?.get("Availabilty")=="0"){
                holder.cardo.setBackgroundResource(R.drawable.backfornotavail)
                holder.plus.visibility=View.GONE
                holder.minus.visibility=View.GONE
                holder.k.visibility=View.GONE
                holder.butt.visibility=View.VISIBLE
            }
        }

        holder.progi.visibility=View.GONE
        holder.x.text=data.get((list.get(position)).toString())?.get("Name").toString()
        holder.y.text="Price - "+data.get(list.get(position).toString())?.get("Price").toString()
        holder.z.text="Rating - "+data[list.get(position).toString()]?.get("Ratings")
        Glide.with(conti).load(data[list.get(position).toString()]?.get("URL")).into(holder.image)
        val db11=Firebase.firestore
        db11.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
            val hihi = it.data?.get("carts") as HashMap<String,Int>
            holder.k.text=hihi.get(list.get(position).toString()).toString()
        }
        //

//        db.collection("Users").get().addOnSuccessListener {
//            for(document in it){
//                if(document.id==FirebaseAuth.getInstance().getCurrentUser()?.getUid()){
//
//                    //
//
//
//                    //
//
//                    var demo = document.data.get("carts") as HashMap<String, Int>
//                    cartii=demo
//                    holder.k.text=cartii.get((position+1).toString()).toString()
//                    x= cartii.get((position+1).toString()).toString()
//                    if(x=="0"){
//                        holder.cardo.visibility=View.GONE
//                        holder.progi.visibility=View.GONE
//                    }
//                    else{
//                        holder.progi.visibility=View.GONE
//                        holder.cardo.visibility=View.VISIBLE
//                    }
//                    y= (document.data.get("fav") as HashMap<String, Int>).get((position+1).toString())!!
//                    if(y==1){
//                        holder.blacki.visibility=View.GONE
//                        holder.red.visibility=View.VISIBLE
//                    }
//                    else{
//                        holder.blacki.visibility=View.VISIBLE
//                        holder.red.visibility=View.GONE
//                    }
//
//                }
//            }
//        }
//
//            holder.x.text=data[(position+1).toString()]?.get("Name")
//            holder.y.text="Price - "+data[(position+1).toString()]?.get("Price")
//            holder.z.text="Rating - "+data[(position+1).toString()]?.get("Ratings")
//            holder.image.setImageResource(R.drawable.baseline_person_24)
////            holder.k.text=cartii.get((position+1).toString()).toString()
////        image_url[position]?.let { holder.image.setImageResource(it) }
//            Glide.with(conti).load(data[(position+1).toString()]?.get("URL")).into(holder.image)


        holder.plus.setOnClickListener {
            val dialog = Dialog(conti)
            dialog.setContentView(R.layout.dialog)
            dialog.findViewById<TextView>(R.id.message).text="Updating Cart..."
            dialog.show()
            holder.cardo.alpha=0.5F
            val db=Firebase.firestore
            db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
                var demo=it.data?.get("carts") as HashMap<String,Int>
                demo[(list.get(position)).toString()]= demo[(list.get(position)).toString()]!!+1
                db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("carts",demo).addOnSuccessListener {
                    holder.k.text= demo[(list.get(position)).toString()].toString()
                    Toast.makeText(conti,"Updated the Cart",Toast.LENGTH_SHORT).show()
                    dialog.hide()
                    holder.cardo.alpha=1F
                }
            }

        }

        holder.minus.setOnClickListener {
            val dialog = Dialog(conti)
            dialog.setContentView(R.layout.dialog)
            dialog.findViewById<TextView>(R.id.message).text="Updating Cart..."
            dialog.show()
            holder.cardo.alpha=0.5F
            val db=Firebase.firestore
            db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
                var demo=it.data?.get("carts") as HashMap<String,Int>
                demo[(list.get(position)).toString()]= demo[(list.get(position)).toString()]!!-1
                if(demo[(list.get(position)).toString()]==0){
                    holder.cardo.visibility=View.GONE
                    db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
                        val listi = it.data?.get("cart") as MutableList<Int>
                        listi.removeAt(position)
                        if(listi.size==0){
                            conti.startActivity(Intent(conti,MainActivity::class.java))
                        }
                        db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("cart",listi).addOnSuccessListener {
//                            Toast.makeText(conti,"List Updated",Toast.LENGTH_SHORT).show()
                        }
                    }

                }
                db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).update("carts",demo).addOnSuccessListener {
                    holder.k.text= demo[(list.get(position)).toString()].toString()
                    Toast.makeText(conti,"Updated",Toast.LENGTH_SHORT).show()
                    dialog.hide()
                    holder.cardo.alpha=1F
                }
            }
        }




//        holder.y.text=data[(position+1).toString()]?.get("phone")

    }
}
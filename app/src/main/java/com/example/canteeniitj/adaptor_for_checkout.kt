package com.example.canteeniitj

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class adaptor_for_checkout(val conti:Context,var list:MutableList<Int>,var data:HashMap<String,Map<String,String>>):RecyclerView.Adapter<adaptor_for_checkout.view_holder>() {
    class view_holder(itemView: View):RecyclerView.ViewHolder(itemView){
        val x=itemView.findViewById<TextView>(R.id.item_name)
        val y=itemView.findViewById<TextView>(R.id.item_quantity)
        val z=itemView.findViewById<TextView>(R.id.item_price)
        val row=itemView.findViewById<LinearLayout>(R.id.linearLayout)
        val line:View=itemView.findViewById(R.id.horizontalline)
        var total=0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemView:View=LayoutInflater.from(parent.context).inflate(R.layout.checkout,parent,false)
        return view_holder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
        val db=Firebase.firestore
        var carts:HashMap<String,Int>
        var cart:MutableList<Int>
        var quantity:Int
        var price_of_each:Int
        var total_price:Int
        db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {

                    carts = it.data?.get("carts") as HashMap<String, Int>
                    cart=it.data?.get("cart") as MutableList<Int>

                    quantity= carts.get(cart.get(position).toString())!!
                    price_of_each= data.get(cart.get(position).toString())?.get("Price")?.toInt()!!
                    total_price=price_of_each*quantity

                    holder.x.text=data.get(cart.get(position).toString())?.get("Name")
                    holder.y.text=quantity.toString()
                    holder.z.text="Rs. "+total_price.toString()
                    holder.total+=total_price
                    db.collection("Menu_Items").document(list.get(position).toString()).get().addOnSuccessListener {
                        if(it.data?.get("Availabilty")=="0"){
                            holder.row.visibility=View.GONE
                            holder.line.visibility=View.GONE
                        }
                    }




        }
    }
}
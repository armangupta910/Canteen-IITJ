package com.example.canteeniitj

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class adaptor_for_current_order(val conti:Context,var list:MutableList<String>,var orders:Map<String,Int>,var data:HashMap<String,Map<String,String>>):RecyclerView.Adapter<adaptor_for_current_order.view_holder>() {
    class view_holder(itemView: View):RecyclerView.ViewHolder(itemView){
        val item:TextView = itemView.findViewById(R.id.item_name)
        val quant:TextView = itemView.findViewById(R.id.item_quantity)
        val price:TextView=itemView.findViewById(R.id.item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemView:View=LayoutInflater.from(parent.context).inflate(R.layout.checkout,parent,false)
        return view_holder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
        holder.price.visibility=View.GONE
        holder.item.text=data.get(list.get(position))?.get("Name")
        holder.quant.text=orders.get(list.get(position)).toString()
    }
}
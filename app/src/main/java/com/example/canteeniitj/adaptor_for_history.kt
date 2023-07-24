package com.example.canteeniitj

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.util.Date

class adaptor1_for_history(val conti:Context,var listi:MutableList<String>,var list:HashMap<String,Int>,var data:HashMap<String,Map<String,String>>,var total:Int):RecyclerView.Adapter<adaptor1_for_history.view_holder>() {
    class view_holder(itemView: View):RecyclerView.ViewHolder(itemView){
        val x=itemView.findViewById<TextView>(R.id.item_name)
        val y=itemView.findViewById<TextView>(R.id.item_quantity)
        val z=itemView.findViewById<TextView>(R.id.item_price)
        val card=itemView.findViewById<LinearLayout>(R.id.linearLayout)
        val butt=itemView.findViewById<Button>(R.id.share_bill)
//        var total=0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemView:View=LayoutInflater.from(parent.context).inflate(R.layout.checkout,parent,false)
        return view_holder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
            holder.x.text=data.get(listi[position])?.get("Name")
            holder.y.text=list.get(listi[position]).toString()
            val price=list.get("-1")
            holder.z.visibility=View.GONE


//        holder.z.text= ((list.get(listi[position]))?.times(data.get(listi[position].toString())?.get("Price")?.toInt()!!)).toString()
    }
}
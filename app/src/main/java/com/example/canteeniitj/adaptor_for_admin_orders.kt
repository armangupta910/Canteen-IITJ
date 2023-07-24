package com.example.canteeniitj

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class adaptor_for_admin_orders(val conti:Context,var list:MutableList<String>,var orders:Map<String,HashMap<String,Int>>,var data:HashMap<String,Map<String,String>>,var ref:String):RecyclerView.Adapter<adaptor_for_admin_orders.view_holder>() {
    class view_holder(itemView: View):RecyclerView.ViewHolder(itemView){
        var time=itemView.findViewById<TextView>(R.id.time_of_order)
        var date:TextView=itemView.findViewById(R.id.date_of_order)
        var bill:TextView=itemView.findViewById(R.id.total_price)
        var view_order:Button=itemView.findViewById(R.id.view_bill)
        var name:TextView=itemView.findViewById(R.id.customer)
        var card:CardView=itemView.findViewById(R.id.cardformenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemView:View=LayoutInflater.from(parent.context).inflate(R.layout.cardforhistory,parent,false)
        return view_holder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
        holder.name.visibility=View.VISIBLE
        holder.time.setText("Time - "+list.get(position).substring(11,19))
        holder.date.setText("Date - "+list.get(position).substring(0,10))
        Firebase.firestore.collection("Users").document(list.get(position).substring(19)).get().addOnSuccessListener {
            Log.d(TAG,"data => ${it.data}")
            holder.name.text=(it.data?.get("name").toString())
        }
        var x:Int = 0
        val hehe:HashMap<String,Int>
        hehe = orders.get(list.get(position)) as HashMap<String, Int>
        var a:MutableList<String> = mutableListOf()
        var b:Map<String,Int> = hehe as Map<String, Int>
        for(i in hehe){
            a.add(i.key)
            val price=data.get(i.key)?.get("Price")?.toInt()
            val quant=i.value
            x+= price?.times(quant) ?: 0
        }
        holder.bill.text="Bill - Rs."+x.toString()

        val dialog=Dialog(conti)
        dialog.setContentView(R.layout.activity_view_bill)
        if(ref=="1"){
            dialog.findViewById<Button>(R.id.serve).visibility=View.GONE
            holder.view_order.setText("Served")
        }

        holder.view_order.setOnClickListener {

            //Demo
            val x=dialog.findViewById<RecyclerView>(R.id.recycler_for_view_bill)
            var demo:MutableList<String>
            val y=adaptor_for_current_order(conti,a,b,data)
            x.layoutManager= LinearLayoutManager(conti, LinearLayoutManager.VERTICAL,false)
            x.adapter=y
            dialog.show()


            dialog.findViewById<Button>(R.id.serve).setOnClickListener {
                dialog.hide()
                holder.card.visibility=View.GONE
                var demo:HashMap<String,HashMap<String,Int>> = hashMapOf()
                var prev:HashMap<String,HashMap<String,Int>> = hashMapOf()
                Firebase.firestore.collection("previous").document("previous").get().addOnSuccessListener {
                    prev=it.data?.get("previous") as HashMap<String, HashMap<String, Int>>
                    prev.put(list.get(position), orders.get(list.get(position))!!)
                    Firebase.firestore.collection("previous").document("previous").update("previous",prev)
                }
                Firebase.firestore.collection("orders").document("orders").get().addOnSuccessListener {
                    demo=it.data?.get("orders") as HashMap<String, HashMap<String, Int>>

                    demo.remove(list.get(position))
                    Firebase.firestore.collection("orders").document("orders").update("orders",demo)
                    Toast.makeText(conti,"Orders Served",Toast.LENGTH_SHORT).show()

                }
            }
            //Demo

//            val intent=Intent(conti,view_bill::class.java)
//            intent.putExtra("orderid",list.get(position))
//            conti.startActivity(intent)
        }
    }
}
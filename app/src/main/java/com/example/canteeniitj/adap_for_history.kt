package com.example.canteeniitj

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class adaptor_for_history(
    var datetime:List<String>,
    var deta: HashMap<String, Map<String, String>>):RecyclerView.Adapter<adaptor_for_history.view_holder>() {

    class view_holder(itemview:View):RecyclerView.ViewHolder(itemview){
        var x:TextView=itemview.findViewById(R.id.date_of_order)
        var y:TextView=itemview.findViewById(R.id.time_of_order)
        var z:TextView=itemview.findViewById(R.id.total_price)
        var bill:Button=itemview.findViewById(R.id.view_bill)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var itemview:View=LayoutInflater.from(parent.context).inflate(R.layout.cardforhistory,parent,false)
        return view_holder(itemview)
    }

    override fun getItemCount(): Int {
        return datetime.size
    }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
        holder.x.text="Date - "+datetime.get(position).subSequence(0,10)
        holder.y.text="Time - "+datetime.get(position).substring(11)
//        holder.z.text=data.get(datetime[position])?.get("-1").toString()

        var data1:HashMap<String,HashMap<String,Int>> = hashMapOf()
        var data2:HashMap<String,Int>
        var total:Int=0
        Firebase.firestore.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser()?.getUid().toString()).get().addOnSuccessListener {
            data1=it.data?.get("orders") as HashMap<String, HashMap<String, Int>>
            data2=data1.get(datetime[position]) as HashMap<String, Int>
            Log.d(TAG,"${data2}")

            for(i in data2){
                total += deta.get(i.key)?.get("Price")?.toInt()!! * i.value
            }

            holder.z.text= "Bill - "+total.toString()
        }
        holder.bill.setOnClickListener {
            val intent=Intent(it.context,history_bill::class.java)
            intent.putExtra("order_id",datetime.get(position))
            it.context.startActivity(intent)
        }



//        holder.y.text=data[(position+1).toString()]?.get("phone")

    }
}
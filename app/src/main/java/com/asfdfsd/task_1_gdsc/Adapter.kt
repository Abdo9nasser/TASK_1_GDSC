package com.asfdfsd.task_1_gdsc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asfdfsd.task_1_gdsc.Database.Studentmodel

class Adapter(var LIST:ArrayList<Studentmodel>) : RecyclerView.Adapter<Adapter.viewholder>() {
    class viewholder(var view:View):RecyclerView.ViewHolder(view)
    {
        var name:TextView=view.findViewById(R.id.t_name)
        var id=view.findViewById<TextView>(R.id.t_id)
        val email=view.findViewById<TextView>(R.id.t_email) 
        val bu_de=view.findViewById<Button>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
val view=LayoutInflater.from(parent.context).inflate(R.layout.item_st,parent,false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
    val host=LIST.get(position)
    holder.name.text=host.name
        holder.id.text= host.id.toString()
        holder.email.text=host.email
        holder.bu_de.setOnClickListener{
            object_0n?.onclilck(position,host.id)
        }



    }

    var object_0n:onclick_delete? = null
    override fun getItemCount(): Int {
       return LIST.size
    }
    interface onclick_delete
    {
        fun onclilck(position: Int,id:Int)
    }
}
package com.example.miniproyecto2_rafaelruiz_clavijo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class ContactAdapter(var contactData: MutableList<Contact>): RecyclerView.Adapter<ContactAdapter.ObjectDataHolder>(){


    inner class ObjectDataHolder(val view_val: View): RecyclerView.ViewHolder(view_val) {
        fun render(object_item:Contact){
            val name_id = view_val.findViewById<TextView>(R.id.text_view_contactname)
            val phone_id = view_val.findViewById<TextView>(R.id.text_view_number)
            name_id.text = object_item.name
            phone_id.text = object_item.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectDataHolder {
        val my_view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_layout,parent,false) //No se como funciona pero se copia y pega
        return ObjectDataHolder(my_view)
    }

    override fun getItemCount(): Int {
        return contactData.size
    }

    override fun onBindViewHolder(holder: ObjectDataHolder, position: Int) {
        holder.render(contactData[position])
    }

    fun addItem(item: Any) {
        if (item is Contact){
            contactData.add(item)
            notifyDataSetChanged()
        }
        this.notifyDataSetChanged()
    }
}
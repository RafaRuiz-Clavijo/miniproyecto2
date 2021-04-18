package com.example.miniproyecto2_rafaelruiz_clavijo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class ListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter : AdapterInterface

    private var listener: OnClickLitsener? = null

    var contactList = mutableListOf<Contact>()
    lateinit var my_adapter : ContactAdapter

    var new_contact: String? = ""
    var new_number: String? = ""
    var index = 0
    private lateinit var com: Comunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        try {
            index = arguments?.getInt("index")!!
        }
        catch(e: Exception){
            index = 0
        }
        var i = 0
        var my_contact : ArrayList<String>
        while (i < index){
            my_contact = arguments?.getStringArrayList("contact ${i}") as ArrayList<String>
            if (my_contact != null){
                new_contact = my_contact[0]
                new_number = my_contact[1]
                contactList.add(Contact(new_contact!!,new_number!!))
            }
            i += 1
        }
        my_adapter = ContactAdapter(contactList)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = my_adapter
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickLitsener) {
            listener = context
        }
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button_id = view.findViewById<Button>(R.id.button_add)
        com = activity as Comunicator
        button_id.setOnClickListener(){
            com.contact_comunicate(contactList,ContactFragment())
        }
    }

}
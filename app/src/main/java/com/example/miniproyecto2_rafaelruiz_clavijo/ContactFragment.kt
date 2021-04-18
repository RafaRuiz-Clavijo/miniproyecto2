package com.example.miniproyecto2_rafaelruiz_clavijo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import java.lang.Exception

class ContactFragment : Fragment() {

    private var listener: OnClickLitsener? = null
    private lateinit var com: Comunicator

    var contactList : MutableList<Contact> = mutableListOf<Contact>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        var index = 0
        var new_contact: String? = ""
        var new_number: String? = ""
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
        val button_id = view.findViewById<Button>(R.id.button_save)
        com = activity as Comunicator

        button_id.setOnClickListener(){
            val new_name = view.findViewById<EditText>(R.id.editTextName)
            val new_number = view.findViewById<EditText>(R.id.phoneTextName)
            val new_name_text = new_name.text.toString()
            val new_number_text = new_number.text.toString()
            val new_contact = Contact(new_name_text, new_number_text)

            contactList.add(new_contact)

            com.contact_comunicate(contactList,ListFragment())

        }
    }

}
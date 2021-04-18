package com.example.miniproyecto2_rafaelruiz_clavijo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import java.io.File

class MainActivity : AppCompatActivity(), OnClickLitsener, Comunicator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoadFragment(ListFragment())

    }

    override fun onClickItem(item: Any) {
    }

    fun LoadFragment(fragment: Fragment) {
        val new_fragment = supportFragmentManager.beginTransaction()
        new_fragment.replace(R.id.framelayout, fragment, "fragment_tag")
        new_fragment.addToBackStack(null)
        new_fragment.commit()
    }

    override fun contact_comunicate(contact: List<Contact>, fragment: Fragment) {
        val bundle = Bundle()
        var new_contact: Contact
        var my_contact : ArrayList<String>
        var index = 0
        for (i in contact){
            my_contact = arrayListOf(i.name,i.phone)
            bundle.putStringArrayList("contact ${index}",my_contact)
            index+= 1
        }
        bundle.putInt("index",contact.size)
        fragment.arguments = bundle
        val new_fragment = supportFragmentManager.beginTransaction()
        new_fragment.replace(R.id.framelayout,fragment,"fragment_tag")
        new_fragment.addToBackStack(null)
        new_fragment.commit()
    }
}
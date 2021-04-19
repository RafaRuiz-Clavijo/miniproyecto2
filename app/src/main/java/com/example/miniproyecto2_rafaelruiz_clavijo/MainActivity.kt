package com.example.miniproyecto2_rafaelruiz_clavijo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), OnClickLitsener, Comunicator {

    val filename = "example.txt"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contacts = LoadData()
        contact_comunicate(contacts,ListFragment())

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
        SaveData(contact)
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

    fun SaveData(information : List<Contact>){
        try{
            val myfile: FileOutputStream
            myfile = openFileOutput(filename, Context.MODE_PRIVATE)
            for (i in information){
                val contact = i.name + "," + i.phone + '\n'
                myfile.write(contact.toByteArray())
                }
            myfile.close()
            }
        catch(e: Exception){
            val dat = 0
        }
    }

    fun LoadData() : MutableList<Contact>{
        var returner_list = mutableListOf<Contact>()
        try{
            var filein : FileInputStream? = null
            filein = openFileInput(filename)

            var inputStreamRead: InputStreamReader = InputStreamReader(filein)
            val buffer: BufferedReader = BufferedReader(inputStreamRead)

            val stringBuilder: StringBuilder = StringBuilder()

            var text: String? = null

            while({text = buffer.readLine(); text}() != null) {
                if (text!=null) {
                    val info = text!!.split(",").toTypedArray()
                    returner_list.add(Contact(info[0],info[1]))
                }

            }

            val my_text = stringBuilder.toString()
        }
        catch(e: Exception){
            val dat = 0
        }
        return returner_list
    }
}
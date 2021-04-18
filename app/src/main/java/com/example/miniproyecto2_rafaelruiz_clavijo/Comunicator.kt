package com.example.miniproyecto2_rafaelruiz_clavijo

import androidx.fragment.app.Fragment

interface Comunicator {
    fun contact_comunicate(contact: List<Contact>, fragment: Fragment)
}
package com.example.preuabachatfirebase

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer

import com.google.firebase.firestore.FirebaseFirestore



object Singletons {
    //declaration of every singleton instance in the app.
    val database = Database()
    val db = FirebaseFirestore.getInstance()
    var connection = false

}

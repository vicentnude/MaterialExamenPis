package com.example.preuabachatfirebase

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat_view.*
import java.io.File

class chatView : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    lateinit var ctxt: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_view)
        textViewLogin.setText( intent.getStringExtra(Intent.EXTRA_TEXT).toString());

        //writeNewUser(intent.getStringExtra(Intent.EXTRA_TEXT).toString(),intent.getStringExtra(Intent.EXTRA_TEXT).toString(),intent.getStringExtra(Intent.EXTRA_TEXT).toString())
        //var s:String= intent.getStringExtra(Intent.EXTRA_TEXT).toString()+"/user"
        //database.reference
        //val myRef = database.getReference(s)
        //myRef.setValue(Math.random())




    }
    private fun writeNewUser(userId: String, name: String, email: String?) {
        val user = User(name, email)
        database.reference.child("users").child(userId).setValue(user)
    }




}

data class User(
    var username: String? = "",
    var email: String? = ""
)
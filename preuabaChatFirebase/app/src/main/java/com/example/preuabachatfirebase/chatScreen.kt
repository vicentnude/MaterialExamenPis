package com.example.preuabachatfirebase

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_chat_screen.*
import kotlinx.android.synthetic.main.activity_chat_view.*

class chatScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)
        setSupportActionBar(toolbar)
        Singletons.database.currentUser!!.score=(Singletons.database.currentUser!!.score.toInt()+1).toString()
        textView.text=Singletons.database.currentUser!!.username+"   "+Singletons.database.currentUser!!.score

        saveDatabase()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
    fun saveDatabase(){

        var newuser:MutableMap<String,Any> = HashMap<String,Any>()
        newuser["score"] = Singletons.database.currentUser!!.score

        if (!Singletons.database.currentUser!!.email.equals("")){
            Singletons.db.collection("users").document(Singletons.database.currentUser!!.email).update(newuser)
        }
    }

}

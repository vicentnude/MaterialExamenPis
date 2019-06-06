package com.example.preuabachatfirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent



class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        Singletons.database.initDataBase()

        fun showMessage(msg:String){
            Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
        }



        fun exists2(mail:String):Boolean{//check if user exists
            val tmp = Singletons.database.users
            for (u:User1 in tmp){
                if (u.email.equals(mail)){
                    return true
                }
            }
            return false}


            fun register(username:String,mail:String,password:String){//register user if does not exists
                if (!exists2(mail)){
                    Singletons.database.addUser(username,mail,password,0.toString())
                    showMessage("Register Succesful!")
                    // fragact.onBackPressed()
                }
                else{
                    showMessage("Already Registered!")
                }
            }


        fun localSignin(mail:String,password:String){//local signin without google
            Singletons.database.setUserLocal(mail,password)
            if (Singletons.database.currentUser != null){
                showMessage("Login Successful, please wait!")
                Singletons.connection = true
            }

            else{
                showMessage("Loggin Unsuccesful!")
            }
        }


        registerBtn.setOnClickListener(){
           /* auth.createUserWithEmailAndPassword(userText.text.toString(), passwordText.text.toString())
                .addOnCompleteListener{
                    if (!it.isSuccessful()) {
                        val e = it.getException() as FirebaseAuthException
                        Toast.makeText(this@MainActivity, "Failed Registration: " + e.message, Toast.LENGTH_SHORT)
                            .show()
                        //message.hide()
                        //return
                    }

                }*/
            register(userText.text.toString(),userText.text.toString(), passwordText.text.toString())

        }
        loginBtn.setOnClickListener(){
           /* auth.signInWithEmailAndPassword(userText.text.toString(), passwordText.text.toString())
                .addOnCompleteListener{
                    if (!it.isSuccessful()) {
                        val e = it.getException() as FirebaseAuthException
                        Toast.makeText(this@MainActivity, "Failed Registration: " + e.message, Toast.LENGTH_SHORT).show()
                    }
                    else{




                        val intent = Intent(this, chatView::class.java)
                        intent.putExtra(Intent.EXTRA_TEXT, userText.text.toString())
                        startActivity(intent)
                    }

                }

        */
            localSignin(userText.text.toString(), passwordText.text.toString());
            val intent = Intent(this, chatScreen::class.java)
            intent.putExtra(Intent.EXTRA_TEXT, userText.text.toString())
            startActivity(intent)
        }



         }
    }




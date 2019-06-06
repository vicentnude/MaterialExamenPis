package com.example.preuabachatfirebase

import android.widget.Toast
import com.google.android.gms.tasks.Task
import java.io.ByteArrayOutputStream
import java.util.ArrayList
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class Database() {
    var users: ArrayList<User1> = ArrayList()
    var currentUser: User1? = null

    fun initDataBase(){
        val read: Task<QuerySnapshot> = Singletons.db.collection("users")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    for (doc:QueryDocumentSnapshot in it.result!!){
                        //reading firebase users data
                        val name = doc.get("name").toString()
                        val email = doc.get("email").toString()
                        val password = doc.get("password").toString()



                        if(!name.equals(null)){
                            val score = doc.get("score").toString()
                            addUser(name, email, password,score)
                        }
                    }
                }
            }

    }

    fun addUser(name:String,email:String,password:String,score:String):Boolean{
        if (checkUserLocal(email,password) == null){
            var newuser:MutableMap<String,Any> = HashMap<String,Any>() as MutableMap<String, Any>
            //creating firebase parameters
            newuser["name"] = name
            newuser["email"] = email
            newuser["password"] = password
            newuser["score"] = score
            ByteArrayOutputStream()


            //sending parameters to firebase
            Singletons.db.collection("users").document(email).set(newuser)
            //creating local user
            val u = User1(name,email,password,score)

            users.add(u)
            return true
        }
        return false
    }



    fun checkUserLocal(mail:String,password: String):User1?{
        for (user:User1 in users){
            if (user.email.equals(mail) && user.password.equals(password) && !user.password.equals("")){
                return user
            }
        }
        return null
    }

    fun setUserLocal(mail:String,password: String){
        currentUser = checkUserLocal(mail,password)
    }

    fun signOff(){
        currentUser = null
    }

}

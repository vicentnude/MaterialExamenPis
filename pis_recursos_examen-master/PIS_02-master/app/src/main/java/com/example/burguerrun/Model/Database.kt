package com.example.burguerrun.Model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.burguerrun.Singletons
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap

class Database() {
    var users:ArrayList<User> = ArrayList()
    var currentUser: User? = null

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
                        val byte = android.util.Base64.decode(doc.get("avatar").toString(),android.util.Base64.DEFAULT)
                        val avatar = BitmapFactory.decodeByteArray(byte,0,byte.size)
                        var wonLvl = BooleanArray(4)
                        var points = LongArray(4)
                        wonLvl.set(0,doc.get("level1") as Boolean)
                        wonLvl.set(1,doc.get("level2") as Boolean)
                        wonLvl.set(2,doc.get("level3") as Boolean)
                        wonLvl.set(3,doc.get("level4") as Boolean)
                        points.set(0,doc.get("point1") as Long)
                        points.set(1,doc.get("point2") as Long)
                        points.set(2,doc.get("point3") as Long)
                        points.set(3,doc.get("point4") as Long)
                        //creating local data
                        addUser(name, email, password, avatar,wonLvl,points)
                    }
                }
            }

    }

    fun addUser(name:String,email:String,password:String,avatar:Bitmap,wonLvl:BooleanArray,points:LongArray):Boolean{
        if (checkUserGoogle(email) == null && checkUserLocal(email,password) == null){
            var newuser:MutableMap<String,Any> = HashMap<String,Any>()
            //creating firebase parameters
            newuser["name"] = name
            newuser["email"] = email
            newuser["password"] = password
            newuser["level1"] = wonLvl[0]
            newuser["level2"] = wonLvl[1]
            newuser["level3"] = wonLvl[2]
            newuser["level4"] = wonLvl[3]
            newuser["point1"] = points[0]
            newuser["point2"] = points[1]
            newuser["point3"] = points[2]
            newuser["point4"] = points[3]
            var temp:ByteArrayOutputStream = ByteArrayOutputStream()
            avatar.compress(Bitmap.CompressFormat.PNG,100,temp)
            val byte = temp.toByteArray()
            newuser["avatar"] = android.util.Base64.encodeToString(byte,android.util.Base64.DEFAULT)
            //sending parameters to firebase
            Singletons.db.collection("users").document(email).set(newuser)
            //creating local user
            val u = User(name,email,password, avatar)
            u.wonLvl = wonLvl
            u.points = points
            users.add(u)
            return true
        }
        return false
    }

    fun checkUserGoogle(mail:String):User?{
        for (user:User in users){
            if (user.email.equals(mail)){
                return user
            }
        }
        return null
    }

    fun setUserGoogle(mail:String){
        currentUser = checkUserGoogle(mail)
    }

    fun checkUserLocal(mail:String,password: String):User?{
        for (user:User in users){
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

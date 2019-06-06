package com.example.burguerrun.Model

import android.graphics.Bitmap

class User(username:String, email:String , password:String , avatar:Bitmap){

    var username = username
    var email = email
    var password = password
    var avatar = avatar
    var wonLvl:BooleanArray = BooleanArray(4)
    var points:LongArray = LongArray(4)

}

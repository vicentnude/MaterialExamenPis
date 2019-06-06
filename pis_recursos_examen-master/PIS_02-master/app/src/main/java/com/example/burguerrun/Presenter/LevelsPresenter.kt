package com.example.burguerrun.Presenter

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.example.burguerrun.Model.Level
import com.example.burguerrun.Model.User
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import kotlinx.android.synthetic.main.activity_levels.view.*

class LevelsPresenter():Presenter {

    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt:Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity

    override fun setView(view: View){
        this.v = view;
    }

    override fun setActivity(activity: Activity) {
        this.actv = activity
    }

    override fun setFragmentActivity(fragmentActivity: FragmentActivity) {
        this.fragact = fragmentActivity
    }

    override fun setContext(context: Context) {
        this.ctxt = context
    }

    //checking which levels are locked or unlocked by the player
    fun initNivells():BooleanArray{
        if (Singletons.database.currentUser == null){//offline user
            Singletons.database.currentUser = User("","","",  BitmapFactory.decodeResource(v.resources, R.drawable.burger))
        }

        var  state = Singletons.database.currentUser!!.wonLvl//load state Array

        //locking levels
        if (!state[0]){
            v.findViewById<ImageButton>(R.id.lvl2).setColorFilter(v.resources.getColor(R.color.black))
        }
        if (!state[1]){
            v.findViewById<ImageButton>(R.id.lvl3).setColorFilter(v.resources.getColor(R.color.black))
        }
        if (!state[2]){
            v.findViewById<ImageButton>(R.id.lvl4).setColorFilter(v.resources.getColor(R.color.black))
        }
        return state
    }

    override fun showMessage(msg:String){//shortcut for toasts
        Toast.makeText(ctxt,msg, Toast.LENGTH_LONG).show()
    }
}


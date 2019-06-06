package com.example.burguerrun.Presenter

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Toast
import com.example.burguerrun.Model.Level



class LvlPresenter():Presenter {
    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt:Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity
    //created level
    var lvl = Level()

    override fun setView(v: View){
        this.v = v;
    }

    override fun setActivity(activity: Activity) {
        this.actv = activity
    }

    override fun setContext(context: Context) {
        this.ctxt = context
    }

    override fun setFragmentActivity(fragmentActivity: FragmentActivity) {
        this.fragact = fragmentActivity
    }

    override fun showMessage(msg:String){//shortcut for toasts
        Toast.makeText(ctxt,msg, Toast.LENGTH_LONG).show()
    }

}
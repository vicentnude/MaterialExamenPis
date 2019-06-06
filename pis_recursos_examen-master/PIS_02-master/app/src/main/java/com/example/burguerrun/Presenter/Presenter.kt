package com.example.burguerrun.Presenter

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.view.View

interface Presenter {
    fun setView(view:View)
    fun setContext(context:Context)
    fun setActivity(activity: Activity)
    fun setFragmentActivity(fragmentActivity: FragmentActivity)
    fun showMessage(msg:String)
}
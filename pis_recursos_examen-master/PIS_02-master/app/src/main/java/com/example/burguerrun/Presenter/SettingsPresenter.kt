package com.example.burguerrun.Presenter

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Toast
import com.example.burguerrun.Singletons

class SettingsPresenter():Presenter{
    //setting the view that presenter will manipulate
    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt:Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity
    var sound= true;

    override fun setView(v: View){
        this.v = v;
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

    fun mute(){
        //Singletons.audio.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION,0,0)
        showMessage("Silenciat")
        sound=false;
    }

    fun unmmute(){
        //Singletons.audio.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION,1,1)
        showMessage("So Activat")
        sound= true;
    }

    fun vibrationOn(){
        //Singletons.audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,AudioManager.VIBRATE_SETTING_ON)
        showMessage("Vibration Activada")
    }

    fun vibrationOff(){
        //Singletons.audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_OFF)
        showMessage("Vibration Apagada")
    }

    override fun showMessage(msg:String){
        Toast.makeText(v.context,msg, Toast.LENGTH_LONG).show()
    }


}
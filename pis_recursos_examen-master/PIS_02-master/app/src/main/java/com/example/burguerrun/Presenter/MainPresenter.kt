package com.example.burguerrun.Presenter

import android.app.Activity
import android.content.Context
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import com.example.burguerrun.View.*
import kotlinx.android.synthetic.main.header_layout.view.*

class MainPresenter():Presenter {
    //View,context and activity from Presenter
    lateinit var v: View
    lateinit var ctxt:Context
    lateinit var actv: Activity
    lateinit var fragact: FragmentActivity
    var home = true

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

    override fun showMessage(text:String) {
        Toast.makeText(ctxt, text, Toast.LENGTH_LONG).show()
    }

    fun itemAction(it:MenuItem):Boolean{
        //tmp variables
        var drawer_layout = v.findViewById<DrawerLayout>(R.id.drawer_layout)
        var frame_layout = v.findViewById<FrameLayout>(R.id.mainFrame)
        frame_layout.removeAllViews()

        //actions based on id
        when(it.itemId){

            R.id.nav_home ->{
                it.setChecked(true)
                drawer_layout.closeDrawers()
                changeFrag(R.id.mainFrame, HomeFragment())
                home = true
                return true
            }

            R.id.nav_login -> {
                it.setChecked(true)
                drawer_layout.closeDrawers()
                home = false

                if(!Singletons.connection) {
                    changeFrag(R.id.mainFrame, LoginFragment())
                }

                else{
                    showMessage("Fes signoff per accedir")
                    actv.onBackPressed()
                }

                return true
            }

            R.id.nav_register -> {
                it.setChecked(true)
                drawer_layout.closeDrawers()
                changeFrag(R.id.mainFrame, RegisterFragment())
                home = false
                return true
            }

            R.id.nav_ranking ->{
                it.setChecked(true)
                drawer_layout.closeDrawers()
                home = false
                if(Singletons.connection) {
                    changeFrag(R.id.mainFrame, SelectRankingFragment())
                }

                else{
                    showMessage("Fes login per accedir")
                    actv.onBackPressed()
                }

                return true
            }

            R.id.nav_options -> {
                it.setChecked(true)
                drawer_layout.closeDrawers()
                changeFrag(R.id.mainFrame, SettingsFragment())
                home = false
                return true
            }

            R.id.nav_signoff -> {
                it.setChecked(true)
                drawer_layout.closeDrawers()
                showMessage("Signing Off....")
                changeFrag(R.id.mainFrame, HomeFragment())
                home = true
                Singletons.connection = false
                setHeaderDefault()
                Singletons.database.signOff()
                return true
            }

            else->return false
        }

    }

    fun changeFrag(id:Int,frag:Fragment){
        var fragManager = (actv as FragmentActivity).supportFragmentManager
        fragManager.beginTransaction().add(
            id,
            frag
        ).commit()
    }

    fun setHeaderDefault(){
        var nav_view = v.findViewById<NavigationView>(R.id.navigationView)
        val hv = nav_view.inflateHeaderView(R.layout.header_layout)
        hv.mailheader.setText(ctxt.getString(R.string.default_mail))
        hv.userheader.setText(ctxt.getString(R.string.default_username))
        nav_view.removeHeaderView(nav_view.getHeaderView(0))
    }

    fun setUserHeader(){
        var nav_view = v.findViewById<NavigationView>(R.id.navigationView)
        val hv = nav_view.inflateHeaderView(R.layout.header_layout)
        hv.mailheader.setText(Singletons.database.currentUser!!.email)
        hv.userheader.setText(Singletons.database.currentUser!!.username)
        hv.picheader.setImageBitmap(Singletons.database.currentUser!!.avatar)
        nav_view.removeHeaderView(nav_view.getHeaderView(0))
    }


}
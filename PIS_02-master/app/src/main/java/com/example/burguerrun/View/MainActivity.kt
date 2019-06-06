package com.example.burguerrun.View


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.burguerrun.R
import com.example.burguerrun.Singletons


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar:Toolbar
    private lateinit var dL_layout:DrawerLayout
    private lateinit var nav_view:NavigationView
    private val mainPresenter = Singletons.mainPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        //creating toolbar
        configToolbar()
        //starting with home Fragment
        mainPresenter.changeFrag(R.id.mainFrame,HomeFragment())
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        if(Singletons.database.currentUser != null){
            mainPresenter.setUserHeader()
        }


    }

    private fun initPresenter(){
        mainPresenter.setView(window.decorView.rootView)
        mainPresenter.setActivity(this)
        mainPresenter.setContext(this)
    }

    private fun configToolbar(){
        toolbar = findViewById(R.id.toolBar)
        toolbar.setTitleTextColor(android.graphics.Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        //item selection listener for menu toolbar
        nav_view = findViewById(R.id.navigationView)
        nav_view.setNavigationItemSelectedListener {
            mainPresenter.itemAction(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home->{
                dL_layout = findViewById(R.id.drawer_layout)
                dL_layout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onBackPressed() {
        if(mainPresenter.home){
            finish()
        }
        else{
            if(!Singletons.rankingPresenter.names.isEmpty()){
                Singletons.rankingPresenter.update()
            }
            mainPresenter.changeFrag(R.id.mainFrame,HomeFragment())
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null){
            if (requestCode == 0){
                var realpath = Singletons.registerPresenter.getRealPath(this,data.data)
                Singletons.registerPresenter.setImage(realpath)
                Singletons.registerPresenter.showMessage("Imatge Seleccionada!")
            }
            else if (requestCode == 9999){
                Singletons.loginPresenter.validateSignInGoogle(requestCode,resultCode, data)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            123 ->{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
            }
            else-> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }








}
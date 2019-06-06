package com.example.burguerrun.View

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.ImageButton
import android.widget.Toast
import com.example.burguerrun.Model.User
import com.example.burguerrun.R
import com.example.burguerrun.Singletons

class LevelsActivity : AppCompatActivity() {

    val levelsPresenter = Singletons.levelsPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        val LEVEL = "com.example.burguerrun.level"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
        levelsPresenter.setView(window.decorView.rootView)
        levelsPresenter.setActivity(this)
        levelsPresenter.setContext(this)
        val intent = Intent(this, HistoriaNivell::class.java)
        var wonLvl = levelsPresenter.initNivells()

        findViewById<ImageButton>(R.id.lvl1).setOnClickListener{
            intent.putExtra(LEVEL,"Nivell 1")
            startActivity(intent)
            finish()
        }

        findViewById<ImageButton>(R.id.lvl2).setOnClickListener{
            if (wonLvl[0]){
                intent.putExtra(LEVEL,"Nivell 2")
                startActivity(intent)
                finish()
            }
            else{
                levelsPresenter.showMessage("Nivell Bloquejat")
            }

        }
        findViewById<ImageButton>(R.id.lvl3).setOnClickListener{
            if (wonLvl[1]){
                intent.putExtra(LEVEL,"Nivell 3")
                startActivity(intent)
                finish()
            }
            else{
                levelsPresenter.showMessage("Nivell Bloquejat")
            }

        }
        findViewById<ImageButton>(R.id.lvl4).setOnClickListener{
            if (wonLvl[2]){
                intent.putExtra(LEVEL,"Nivell 4")
                startActivity(intent)
                finish()
            }
            else{
                levelsPresenter.showMessage("Nivell Bloquejat")
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}

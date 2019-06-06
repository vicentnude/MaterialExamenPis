package com.example.burguerrun.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.burguerrun.R
import com.example.burguerrun.Singletons

class HistoriaNivell : AppCompatActivity() {

    lateinit var image_historia: ImageView;
    val LEVEL = "com.example.burguerrun.level"
    val historiaPresenter = Singletons.historiaPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historia_nivell)
        historiaPresenter.setView(window.decorView.rootView)
        historiaPresenter.setActivity(this)
        historiaPresenter.setContext(this)
        image_historia = findViewById(R.id.imageHistoria)
        val intent = intent
        val nivell = intent.getStringExtra(LEVEL)
        if (!nivell.equals("Guanyat")){
            historiaPresenter.createLevel(nivell)
        }
        Singletons.level = historiaPresenter.lvl
        image_historia = historiaPresenter.selectImage(nivell,image_historia)

        val intent2 = Intent(this, LvlActivity::class.java)
        val intent3 = Intent(this, MenuGuanyat::class.java)


        findViewById<Button>(R.id.buttonHistoria).setOnClickListener{
            findViewById<Button>(R.id.buttonHistoria).isEnabled = false
            if(nivell.equals("Guanyat")){
                startActivity(intent3)
            }
            else{
                intent2.putExtra(LEVEL,nivell)
                startActivity(intent2)
            }
            finish()

        }


    }

    override fun onBackPressed() {

    }
}

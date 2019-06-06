package com.example.burguerrun.View

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.burguerrun.Engine.GameView
import com.example.burguerrun.R
import com.example.burguerrun.Singletons

class LvlActivity : AppCompatActivity() {
    lateinit var btnMenuWin: Button;
    lateinit var btnLoseMenu: Button;
    lateinit var textNivell : TextView
    lateinit var screenGameView: GameView;
    val LEVEL = "com.example.burguerrun.level"
    val lvlPresenter = Singletons.lvlPresenter


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl)
        lvlPresenter.setView(window.decorView.rootView)
        lvlPresenter.setActivity(this)
        lvlPresenter.setContext(this)
        val intent = intent
        textNivell = findViewById(R.id.text_nivell)
        textNivell.text = intent.getStringExtra(LEVEL)
        screenGameView = findViewById(R.id.gameView);

        screenGameView.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeRight() {
                screenGameView.goRight()
            }

            override fun onSwipeLeft() {
                screenGameView.goLeft()
            }

            override fun onSwipeBottom() {
                screenGameView.goDown()
            }

            override fun onSwipeTop() {
                screenGameView.goUp();
            }

        })

    }

    override fun onBackPressed() {
        screenGameView.stop()
        Singletons.level.ingredients.clear()
        finish()
    }





}



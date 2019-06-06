package org.manumatnez.recursosexamenpis

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView



class SnakeActivity : AppCompatActivity() {
    private lateinit var backButton : Button
    private lateinit var pauseButton : Button
    private lateinit var surface : GameView
    private lateinit var score : TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake)

        supportActionBar!!.title = getString(R.string.snake_game)

        score = findViewById(R.id.points)

        surface = GameView(this)

        val touchListener = object : OnSwipeTouchListener(this) {
            override fun onSwipeTop() {
                movement = UP
            }

            override fun onSwipeRight() {
                movement = RIGHT
            }

            override fun onSwipeLeft() {
                movement = LEFT
            }

            override fun onSwipeBottom() {
                movement = DOWN
            }
        }

        surface.setOnTouchListener(touchListener)

        val snakeScreen = findViewById<LinearLayout>(R.id.snakeScreen)
        snakeScreen.addView(surface)

        backButton = findViewById(R.id.backBtn)
        backButton.setOnClickListener {
            surface.surfaceDestroyed(surface.holder)
            onBackPressed()
        }

        pauseButton = findViewById(R.id.pauseResumeBtn)
        pauseButton.setOnClickListener {
            if (pause) {
                surface.resumeThread()
                pauseButton.text = getText(R.string.pause)
                surface.setOnTouchListener(touchListener)
            } else {
                onPause()
                surface.setOnTouchListener(null)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        surface.pauseThread()
        pauseButton.text = getText(R.string.resume)
    }

    fun updatePoints(points: Long) {
        runOnUiThread {
            score.text = points.toString()
        }
    }

}

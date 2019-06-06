package com.example.burguerrun.View

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.widget.ProgressBar
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import java.lang.Thread

class SplashActivity : AppCompatActivity() {
    lateinit var progressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        progressBar = findViewById(R.id.progres_splash)
        Thread(Runnable {
            kotlin.run {
                backgroundWork()
                startApp()
                finish()
            }
        }).start()


    }

    private fun backgroundWork(){
        Singletons.database.initDataBase()
        Singletons.audio = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        Singletons.scale = this@SplashActivity.resources.displayMetrics.density
        for (x in 0 until 100 step 22){
            Thread.sleep(1000)
            progressBar.setProgress(x,true)
        }
    }

    private fun startApp(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}


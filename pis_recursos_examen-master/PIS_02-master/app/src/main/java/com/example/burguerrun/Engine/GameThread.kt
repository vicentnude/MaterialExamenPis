package com.example.burguerrun.Engine

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.graphics.Canvas
import android.media.AudioManager
import android.media.SoundPool
import android.os.Vibrator
import android.support.v4.content.ContextCompat.getSystemService
import android.view.SurfaceHolder
import com.example.burguerrun.R
import com.example.burguerrun.Singletons


class GameThread(private val surfaceHolder: SurfaceHolder,context:Context, private val gE: GameEngine) : Thread() {
    private val FPS = 5
    private var running: Boolean = false
    private var context = context
    var gameEngine:GameEngine
    var cont : Int = 0
    lateinit var sound : SoundPool
    var moveSoundPool: Int = 0
    val settingsPresenter = Singletons.settingsPresenter
    lateinit var vibrator : Vibrator

    fun setRunning(isRunning: Boolean) {
        this.running = isRunning
    }
    init{
        this.gameEngine=gE
        sound = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
        moveSoundPool = sound.load(context, R.raw.move, 1)
        vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        val targetTime = (1000 / FPS).toLong()

        while (running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                // locking the canvas allows us to draw on to it
                canvas = this.surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    this.gameEngine.plyr.update()
                    if(this.gameEngine.vibrate == 1){
                        vibrator.vibrate(500)
                        this.gameEngine.vibrate = 0
                    }
                    if (cont % 2 == 0){
                        this.gameEngine.allEnemiesAi()
                        if(this.gameEngine.move() == 1 && this.settingsPresenter.sound){
                            sound.play(moveSoundPool,1.0f, 1.0f, 1, 0, 1.0f)
                        }
                    }


                    this.gameEngine.draw(canvas!!)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            timeMillis = (startTime - System.nanoTime()) / 1000000
            waitTime =  targetTime - timeMillis


            try {
                sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            cont++
        }

    }

    companion object {
        private var canvas: Canvas? = null
    }


}
